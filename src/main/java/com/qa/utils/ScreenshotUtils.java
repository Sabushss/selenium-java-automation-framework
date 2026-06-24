package com.qa.utils;

import com.qa.config.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils - Captures screenshots on test failure
 * Author: Subash A | QA Automation Engineer
 */
public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = ConfigReader.getScreenshotPath();
            Files.createDirectories(Paths.get(screenshotDir));

            String filePath = screenshotDir + testName + "_" + timestamp + ".png";
            File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
