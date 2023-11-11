package manager;

import dto.AddCarDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tests.CarTests;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class CarHelper extends BaseHelper {
    By btnLetTheCarWork = By.xpath("//a[@id='1']");
    By inputLocation = By.id("pickUpPlace");
    By inputLocationHaifa = By.xpath("//div[@class='pac-item']//span[@class='pac-matched']");
    By inputManufacture = By.id("make");
    By inputModel = By.id("model");
    By inputYear = By.id("year");
    By inputFuel = By.id("fuel");
    By inputFuelOption = By.xpath("//option[@value='Hybrid']");
    By inputSeats = By.id("seats");
    By inputCarClass = By.id("class");
    By inputPrice = By.id("price");
    By inputAbout = By.id("about");
    By inputRegNumber = By.id("serialNumber");
    By btnSubmit = By.xpath("//button[@type='submit']");
    By inputPhoto = By.xpath("//label[@for='photos']");
    By textPopUpAddNewCarSuccess = By.xpath("//div[@class='dialog-container']/h1[@class='title']");
    By textCarPhoto = By.xpath("//div[@class='mat-chip-ripple']");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    By errorWrongAddress = By.xpath("//input[@id='pickUpPlace']/..//div");//Wrong address
    By errorMakeIsRequired = By.xpath("//input[@id='make']/..//div");// Make is required
    By errorModelIsRequired = By.xpath("//input[@id='model']/..//div");// Model is required
    By errorYearIsRequired = By.xpath("//input[@id='year']/..//div//div");// Year required //Wrong year
    By errorFuelIsRequired = By.xpath("//select[@id='fuel']/..//div"); //Fuel is required
    By errorSeatsIsRequired = By.xpath("//input[@id='seats']/..//div/div");// Number of seats is required  //Car must have min 2 seat  //To much seats
    By errorClassIsRequired = By.xpath("//input[@id='class']/..//div");// Car class is required
    By errorRegNumberIsRequired = By.xpath("//input[@id='serialNumber']/..//div/div");//Car registration number is required  //To long car registration number
    By errorPriceIsRequired = By.xpath("//input[@id='price']/..//div/div");// Price is required  //To much big price  //Price must be positive




    public By getLocatorPopUp(String manuf, String model) {
        return By.xpath(String.format("//h2[contains(text(), '%s %s added successful')]", manuf, model));
    }

    public CarHelper(WebDriver driver) {
        super(driver);
    }


    public void fillNewCarForm(AddCarDTO car) {
clickBase(btnLetTheCarWork);
        typeTextBase(inputLocation, car.getLocation());
        clickBase(inputLocationHaifa);
        typeTextBase(inputManufacture, car.getManufacture());
        typeTextBase(inputModel, car.getModel());
        typeTextBase(inputYear, String.valueOf(car.getYear()));
        clickBase(inputFuelOption);
        typeTextBase(inputSeats, String.valueOf(car.getSeats()));
        typeTextBase(inputCarClass, car.getCarClass());
        typeTextBase(inputRegNumber, car.getRegNumber());
        typeTextBase(inputPrice, String.valueOf(car.getPrice()));
        typeTextBase(inputAbout, car.getAbout());
      //  addPictureToNewCarForm();

        clickBase(btnSubmit);

    }

    public void fillNewCarNegativeForm(AddCarDTO car) {
        clickBase(btnLetTheCarWork);
        typeTextBase(inputLocation, car.getLocation());
        clickBase(inputLocationHaifa);
        typeTextBase(inputManufacture, car.getManufacture());
        typeTextBase(inputModel, car.getModel());
        typeTextBase(inputYear, String.valueOf(car.getYear()));
        clickBase(inputFuelOption);
        typeTextBase(inputSeats, String.valueOf(car.getSeats()));
        typeTextBase(inputCarClass, car.getCarClass());
        typeTextBase(inputRegNumber, car.getRegNumber());
        typeTextBase(inputPrice, String.valueOf(car.getPrice()));
        typeTextBase(inputAbout, car.getAbout());

    }

    public void addPictureToNewCarForm() {

        clickBase(inputPhoto);
        pause(3);//to open the window
        String fileName = "C:\\Users\\nosni\\Data\\TelRan\\Automation\\IlCarroZip\\iCarroQA20-main\\src\\test\\resources\\car1.png";
        StringSelection str = new StringSelection(fileName);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        pause(3);//to search element
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        pause(3); //to download element
    }

    public boolean validationCarAddedSuccess1() {
        return isTextEqual(textPopUpAddNewCarSuccess, "Car added");
    }

    public boolean validationCarAddedSuccess(String manuf, String model) {
        return isTextEqual(getLocatorPopUp(manuf, model), manuf + " " + model + " added successful");
    }

    public boolean validationPhotoAddedSuccess() {
        return isElementExist(textCarPhoto);
    }


    public void clickBtnNewForm() {
        clickBase(btnLetTheCarWork);
    }
    public boolean validateMessageWrongAddress(){
        return isTextEqual(errorWrongAddress, "Wrong address");
    }

    public boolean validateMessageWrongYear() {
       return (isTextEqual(errorYearIsRequired, "Year required")||isTextEqual(errorYearIsRequired, "Wrong year"));
    }

    public boolean validateMessageWrongSeats() {

        return (isTextEqual(errorSeatsIsRequired, "Number of seats is required")||
                isTextEqual(errorSeatsIsRequired, "Car must have min 2 seat")||
                isTextEqual(errorSeatsIsRequired, "To much seats"));
    }

    public boolean validateMessageWrongPrice() {
        // Price is required  //To much big price  //Price must be positive
        return (isTextEqual(errorPriceIsRequired, "Price is required")||
                isTextEqual(errorPriceIsRequired, "To much big price")||
                isTextEqual(errorPriceIsRequired, "Price must be positive"));
    }

    public boolean validateMessageWrongRegNum() {
        return isTextEqual(errorRegNumberIsRequired, "To long car registration number");
    }

    public void fillNewCarNegativeFormEmptyLocation(AddCarDTO car) {
        clickBase(btnLetTheCarWork);
        typeTextBase(inputLocation, car.getLocation());
        typeTextBase(inputManufacture, car.getManufacture());
        typeTextBase(inputModel, car.getModel());
        typeTextBase(inputYear, String.valueOf(car.getYear()));
        clickBase(inputFuelOption);
        typeTextBase(inputSeats, String.valueOf(car.getSeats()));
        typeTextBase(inputCarClass, car.getCarClass());
        typeTextBase(inputRegNumber, car.getRegNumber());
        typeTextBase(inputPrice, String.valueOf(car.getPrice()));
        typeTextBase(inputAbout, car.getAbout());
    }
}
