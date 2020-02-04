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

package uk.gov.hmrc.enrolmentsorchestrator.config

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.enrolmentsorchestrator.UnitSpec

class AppConfigSpec extends UnitSpec with GuiceOneAppPerSuite {

  val appConfig: AppConfig = app.injector.instanceOf[AppConfig]

  appConfig.auditingEnabled shouldBe true
  appConfig.graphiteHost shouldBe "graphite"
  appConfig.enrolmentsStoreBaseUrl shouldBe "http://localhost:9595"
  appConfig.taxEnrolmentsBaseUrl shouldBe "http://localhost:9995"

}