package tests;

import data.DataProviderAddNewCar;
import dto.AddCarDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarTests extends BaseTest {
    @BeforeClass
    public void preconditions() {
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);

    }

    @AfterClass
    public void postconditionsAfterClass() {

        app.getUserHelper().logout();


    }

    @AfterMethod
    public void postconditions() {
        app.getUserHelper().navigateToMainPage();
    }

    @Test
    public void positiveAddNewCar() {
        AddCarDTO car = AddCarDTO.builder()
                .location("Haifa, Israel")
                .manufacture("Toyota")
                .model("Corolla Cross")
                .year(2022)
                .seats(4)
                .carClass("1")
                .regNumber(randomUtils.generateDigitsString(10))
                .price(192)
                .about("Designed for a balance of power and efficiency.")
                .build();
        app.getCarHelper().fillNewCarForm(car);
        //   Assert.assertTrue(app.getCarHelper().validationPhotoAddedSuccess());
        Assert.assertTrue(app.getCarHelper().validationCarAddedSuccess(car.getManufacture(), car.getModel()));


    }

    @Test
    public void negativeAddNewCar() {
        AddCarDTO car = AddCarDTO.builder()
                .location("")
                .manufacture("Jeep")
                .model("SE-09")
                .year(2023)
                .seats(2)
                .carClass("2")
                .regNumber("76859")
                .price(123)
                .about("")
                .build();
        app.getCarHelper().fillNewCarNegativeFormEmptyLocation(car);
        Assert.assertTrue(app.getCarHelper().validateMessageWrongAddress());

    }

    @Test(dataProvider = "negativeYearAddNewCar", dataProviderClass = DataProviderAddNewCar.class)
    public void negativeYearAddNewCar(AddCarDTO carDP) {
        app.getCarHelper().fillNewCarNegativeForm(carDP);
        Assert.assertTrue(app.getCarHelper().validateMessageWrongYear());


    }

    @Test(dataProvider = "negativeSeatsAddNewCar", dataProviderClass = DataProviderAddNewCar.class)
    public void negativeSeatsAddNewCar(AddCarDTO carDP) {
        app.getCarHelper().fillNewCarNegativeForm(carDP);
        Assert.assertTrue(app.getCarHelper().validateMessageWrongSeats());


    }

    @Test(dataProvider = "negativePriceAddNewCar", dataProviderClass = DataProviderAddNewCar.class)
    public void negativePriceAddNewCar(AddCarDTO carDP) {
        app.getCarHelper().fillNewCarNegativeForm(carDP);
        Assert.assertTrue(app.getCarHelper().validateMessageWrongPrice());


    }

    @Test
    public void negativeRegNumberAddNewCar() {
        AddCarDTO car = AddCarDTO.builder()
                .location("Haifa,Israel")
                .manufacture("Jeep")
                .model("SE-09")
                .year(2023)
                .seats(2)
                .carClass("2")
                .regNumber(randomUtils.generateDigitsString(16))
                .price(123)
                .about("")
                .build();
        app.getCarHelper().fillNewCarNegativeForm(car);
        Assert.assertTrue(app.getCarHelper().validateMessageWrongRegNum());

    }
}
