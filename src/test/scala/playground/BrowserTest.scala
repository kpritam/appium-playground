package playground

import java.net.URL

import io.appium.java_client.MobileDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.{MobileBrowserType, MobileCapabilityType}
import org.openqa.selenium.Platform
import org.openqa.selenium.remote.DesiredCapabilities
import org.scalatest._

class BrowserTest extends FunSuite with Matchers with BeforeAndAfterAll {

  var mobileDriver: MobileDriver[_] = _
  val AppiumURL                     = "http://127.0.0.1:4723/wd/hub"
  val Emulator                      = "emulator-5554"
  val PlatformVersion               = "7.1.1"

  override def beforeAll(): Unit = {
    val capabilities = new DesiredCapabilities()
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PlatformVersion)
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID)
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Emulator)
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME)
    capabilities.setCapability("newCommandTimeout", 2000)
    mobileDriver = new AndroidDriver(new URL(AppiumURL), capabilities)
  }

  override def afterAll(): Unit = mobileDriver.quit()

  test("test browser") {
    mobileDriver.get("http://appium.io/")
    mobileDriver.getCurrentUrl shouldBe "http://appium.io/"
    mobileDriver.getTitle shouldBe "Appium: Mobile App Automation Made Awesome."
  }

}
