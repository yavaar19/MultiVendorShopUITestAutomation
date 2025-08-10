package Utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private final int maxTries = 3;
    private int count = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (count < maxTries) {

            count++;
            return true;

        }

        return false;

    }

}
