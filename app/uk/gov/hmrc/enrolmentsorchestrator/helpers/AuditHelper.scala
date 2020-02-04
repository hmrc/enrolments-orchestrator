/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.enrolmentsorchestrator.helpers
import play.api.Logger
import play.api.libs.json.Json
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.play.audit.http.connector.{AuditConnector, AuditResult}
import uk.gov.hmrc.play.audit.model.ExtendedDataEvent
import uk.gov.hmrc.enrolmentsorchestrator.models._
import scala.concurrent.{ExecutionContext, Future}

trait AuditHelper{
  val AUDIT_SOURCE = "enrolments-orchestrator"
  val auditConnector: AuditConnector
  implicit val executionContext: ExecutionContext

  def audit(event: ExtendedDataEvent)(implicit hc: HeaderCarrier): Future[AuditResult] = {
    auditConnector.sendExtendedEvent(event) recover {
      case t: Throwable ⇒
        Logger error(s"Failed sending audit message", t)
        AuditResult.Failure(s"Failed sending audit message", Some(t))
    }
  }

def auditDeleteRequestEvent(agentDeleteRequest: AgentDeleteRequest): ExtendedDataEvent = {
  val auditType: String = "AgentDeleteRequest"
  ExtendedDataEvent(
    AUDIT_SOURCE,
    auditType,
    detail = Json.obj(
      "ARN"      -> agentDeleteRequest.ARN,
      "terminationDate" -> agentDeleteRequest.terminationDate
    )
  )
}

  def auditAgentDeleteResponseEvent(agentDeleteResponse: AgentDeleteResponse): ExtendedDataEvent = {
    val auditType: String = "AgentDeleteResponse"
    ExtendedDataEvent(
      AUDIT_SOURCE,
      auditType,
      detail = Json toJson agentDeleteResponse
    )
  }


}
