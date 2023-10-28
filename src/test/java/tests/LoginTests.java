package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void preconditionsLogin() {
        logoutIflogin();
    }

    @AfterMethod(alwaysRun = true)
    public void postconditionsLogin() {
        clickOkIfRegistered();
        app.getUserHelper().pause(3);
        app.getUserHelper().refreshPage();
    }

    @Test
    public void positiveLoginUserDTO() {
        UserDTO userDTO = new UserDTO("qwerty1@gmail.com", "User#12345");
        app.getUserHelper().login(userDTO);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test
    public void positiveLoginUserDTOWith() {
        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail("qwerty1@gmail.com")
                .withPassword("User#12345");
        app.getUserHelper().login(userDTOWith);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    //@Test(groups = {"smoke"}, dataProvider = "positiveDataLogin", dataProviderClass = DataProviderLogin.class)
    @Test(dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class)
    public void positiveLogin(UserDtoLombok userDP) {
        app.getUserHelper().loginUserDtoLombok(userDP);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test
    public void negativePasswordWithoutSymbol() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty1@gmail.com")
                .password("123456Aaa")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(groups = {"smoke", "regression"})
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty1@gmail.com")
                .password("ddsdhjAa$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(groups = {"regression"}, dataProvider = "negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class)
    public void negativePasswordWithoutLetters(UserDtoLombok userDP) {
//        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
//                .email("qwerty1@gmail.com")
//                .password("12345678$")
//                .build();
        app.getUserHelper().loginUserDtoLombok(userDP);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}
