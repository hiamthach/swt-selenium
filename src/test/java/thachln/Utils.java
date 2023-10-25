package thachln;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {
  public static void takeScreenshot(WebDriver driver, String destFile) {

    File screenshotDir = new File("screenshot");
    if (!screenshotDir.exists()) {
      screenshotDir.mkdir();
    }
    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File dest = new File(screenshotDir, destFile);
    srcFile.renameTo(dest);
  }
}
