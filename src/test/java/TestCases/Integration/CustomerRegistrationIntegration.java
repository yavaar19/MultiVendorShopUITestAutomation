package TestCases.Integration;

import PageObjects.RegistrationPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CustomerRegistrationIntegration extends Base {

    private RegistrationPage registrationPage;

    @Test(description = "Confirm user can register customer account with valid data", priority = 1)
    public void TC_MVS_IT_038() {

        registrationPage = basePage.clickRegisterCustomerButton();

        registrationPage.clickRegisterCustomerButton();

        registrationPage.setAllFields("John", "Doe", "testcustomer@example.com", "omhRbv7017#", "8153546625", "98 John Doe Lane", "Gotham", "5015");
        registrationPage.registerUser();

        registrationPage.waitForElementToAppear(registrationPage.getRegistrationToast());

        Assert.assertEquals(registrationPage.getToastMessage(), "User registered Successfully", "User was not able to register");

    }

    @Test(description = "Confirm user cannot register a customer account using an existing seller email id", priority = 2)
    public void TC_MVS_IT_039() {

        registrationPage = basePage.clickRegisterCustomerButton();

        registrationPage.clickRegisterCustomerButton();

        registrationPage.setAllFields("Duplicate", "Account", "testcustomer@example.com", "unhKim5687%", "5364578896", "85 Duplicate Lane", "Duplicate", "1547");
        registrationPage.registerUser();

        registrationPage.waitForElementToAppear(registrationPage.getRegistrationToast());

        Assert.assertEquals(registrationPage.getToastMessage(), "User with this Email Id already resgistered!!!", "User was able to register with already existing email id");

    }

}
