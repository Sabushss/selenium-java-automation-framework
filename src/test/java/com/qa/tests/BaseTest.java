package com.qa.tests;

import com.qa.config.ConfigReader;
import com.qa.utils.DriverManager;
import com.qa.utils.ScreenshotUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest - Parent class for all test classes
 * Handles driver setup, teardown and screenshot on failure
 * Author: Subash A | QA Automation Engineer
 */
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.captureScreenshot(result.getName());
        }
        DriverManager.quitDriver();
    }
}
