package com.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Reads configuration from config.properties file
 * Author: Subash A | QA Automation Engineer
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value.trim();
    }

    public static String getBaseUrl()          { return getProperty("base.url"); }
    public static String getBrowser()          { return getProperty("browser"); }
    public static boolean isHeadless()         { return Boolean.parseBoolean(getProperty("headless")); }
    public static int getImplicitWait()        { return Integer.parseInt(getProperty("implicit.wait")); }
    public static int getExplicitWait()        { return Integer.parseInt(getProperty("explicit.wait")); }
    public static int getPageLoadTimeout()     { return Integer.parseInt(getProperty("page.load.timeout")); }
    public static String getTestUsername()     { return getProperty("test.username"); }
    public static String getTestPassword()     { return getProperty("test.password"); }
    public static String getReportPath()       { return getProperty("report.path"); }
    public static String getScreenshotPath()   { return getProperty("screenshot.path"); }
}
