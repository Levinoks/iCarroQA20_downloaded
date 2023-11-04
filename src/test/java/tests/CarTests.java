package tests;

import dto.AddCarDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarTests extends BaseTest {
@BeforeClass
public void preconditions(){
    app.getUserHelper().loginUserDtoLombok(userDtoLombok);
    app.getCarHelper().clickBtnNewForm();
}
@AfterMethod
public void postconditions(){
    app.getUserHelper().navigateToMainPage();
    app.getUserHelper().logout();

}
    @Test
    public void positiveAddNewCar() {
        AddCarDTO car = AddCarDTO.builder()
                .location("Haifa, Israel")
                .manufacture("Toyota")
                .model("Corolla Cross")
                .year("2022")
                .seats("4")
                .carClass("1")
                .regNumber(randomUtils.generateDigitsString(10))
                .price("192")
                .about("Designed for a balance of power and efficiency.")
                .build();
        app.getCarHelper().fillNewCarForm(car);
        Assert.assertTrue(app.getCarHelper().validationCarAddedSuccess());


    }
}
