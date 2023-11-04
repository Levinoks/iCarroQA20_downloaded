package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {


    @AfterMethod(alwaysRun = true)
    public void postconditionsLogin() {

        if (flagPopUp) {
            flagPopUp = false;
            app.getUserHelper().clickOkPopUpSuccessLogin();
        }
        if (flagLogin) {
            flagLogin = false;
            app.getUserHelper().logout();
        }
    }

    @Test(priority = 1)
    public void positiveLoginUserDTO() {
        UserDTO userDTO = new UserDTO("qwerty1@gmail.com", "User#12345");
        app.getUserHelper().login(userDTO);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
        flagLogin = true;
        flagPopUp = true;
        app.getUserHelper().pause(1);
    }

    @Test
    public void positiveLoginUserDTOWith() {
        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail("qwerty1@gmail.com")
                .withPassword("User#12345");
        app.getUserHelper().login(userDTOWith);
        flagLogin = true;
        flagPopUp = true;
        app.getUserHelper().pause(1);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    //@Test(groups = {"smoke"}, dataProvider = "positiveDataLogin", dataProviderClass = DataProviderLogin.class)
    @Test(dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class)
    public void positiveLogin(UserDtoLombok userDP) {
        app.getUserHelper().loginUserDtoLombok(userDP);
        flagLogin = true;
        flagPopUp = true;
        app.getUserHelper().pause(1);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test
    public void negativePasswordWithoutSymbol() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty1@gmail.com")
                .password("123456Aaa")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagPopUp = true;
        app.getUserHelper().pause(1);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(groups = {"smoke", "regression"})
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty1@gmail.com")
                .password("ddsdhjAa$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagPopUp = true;
        app.getUserHelper().pause(1);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(groups = {"regression"}, dataProvider = "negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class)
    public void negativePasswordWithoutLetters(UserDtoLombok userDP) {
        app.getUserHelper().loginUserDtoLombok(userDP);
        flagPopUp = true;
        app.getUserHelper().pause(1);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}
