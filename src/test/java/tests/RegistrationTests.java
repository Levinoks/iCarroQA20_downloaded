package tests;

import data.DataProviderLogin;
import data.DataProviderRegistration;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void preconditionsRegistration() {
        logoutIflogin();
    }

    @AfterMethod(alwaysRun = true)
    public void postconditionsRegistration() {

        clickOkIfRegistered();
        app.getUserHelper().pause(3);
        clickOkIfRegistered();


        app.getUserHelper().refreshPage();
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "positiveDataRegistration", dataProviderClass = DataProviderRegistration.class)
    public void positiveRegistration(UserDtoLombok userDP) {
        String email = randomUtils.generateEmail(7);

//        UserDtoLombok user = UserDtoLombok.builder()
//                .email(email)
//                .password("123456Aa$")
//                .lastName("abdfg")
//                .name("test")
//                .build();

        app.getUserHelper().fillRegistrationForm(userDP);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }

    @Test(enabled=false, dataProvider = "regCSV", dataProviderClass = DataProviderRegistration.class)
    public void positiveOnceRegistration(UserDtoLombok userDP) {
        app.getUserHelper().fillRegistrationForm(userDP);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }

    @Test
    public void negativeRegistrationWrongEmail() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("abc@")
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillNegativeRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmailReg());
    }

    @Test
    public void negativeRegistrationWrongPassword() {
        String email = randomUtils.generateEmail(7);

        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillNegativeRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateMessageWrongPasswordReg());
    }

    @Test(groups = {"smoke"})
    public void negativeRegistrationBlankEmail() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("")
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillNegativeRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
    }
}
