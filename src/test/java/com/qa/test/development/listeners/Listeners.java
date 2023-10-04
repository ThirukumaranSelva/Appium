package com.qa.test.development.listeners;

import com.qa.test.development.base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

public class Listeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) ;
        {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println("Listeners: " + stringWriter);
        }

        BaseClass baseClass = new BaseClass();
        baseClass.getDriver();

        HashMap<String, String> parameter = new HashMap<>();
        parameter = (HashMap<String, String>) result.getTestContext().getCurrentXmlTest().getAllParameters();
        System.out.println(parameter);
        TakesScreenshot screenshot = baseClass.getDriver();
        File input = screenshot.getScreenshotAs(OutputType.FILE);
        String screenshotFile =
                System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                        "java" + File.separator + "com" + File.separator + "qa" + File.separator + "test" + File.separator +
                        "development" + File.separator + "screenshot" + File.separator + parameter.get("deviceName") + " " +
                        parameter.get("platformName") + "v" + parameter.get("platformVersion") + " " + baseClass.getDateAndTime() +
                        " " + result.getName() + ".jpg";

        File file = new File(screenshotFile);
        try {
            FileUtils.copyFile(input, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
