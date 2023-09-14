package com.natwest.utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class ReportLogger {

    public static void logInfo(boolean extentPrint,boolean systemPrint, String text){

        if (systemPrint){
            System.out.println(text);
        }
        if (extentPrint){
            ExtentTest step = ExtentCucumberAdapter.getCurrentStep();
            step.info("Execution log :: <b>" +text + "</b><br/>");
        }

    }
}
