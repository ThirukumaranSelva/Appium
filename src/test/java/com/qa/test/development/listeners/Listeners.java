package com.qa.test.development.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Listeners implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
       if(result.getThrowable()!=null);
        {
            StringWriter stringWriter=new StringWriter();
            PrintWriter printWriter=new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println("Listeners: "+stringWriter);
        }
    }
}
