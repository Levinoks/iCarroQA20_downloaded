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

    public By getLocatorPopUp(String manuf, String model) {
        return By.xpath(String.format("//h2[contains(text(), '%s %s added successful')]", manuf, model));
    }

    public CarHelper(WebDriver driver) {
        super(driver);
    }


    public void fillNewCarForm(AddCarDTO car) {

        typeTextBase(inputLocation, car.getLocation());
        clickBase(inputLocationHaifa);
        typeTextBase(inputManufacture, car.getManufacture());
        typeTextBase(inputModel, car.getModel());
        typeTextBase(inputYear, car.getYear());
        clickBase(inputFuelOption);
        typeTextBase(inputSeats, car.getSeats());
        typeTextBase(inputCarClass, car.getCarClass());
        typeTextBase(inputRegNumber, car.getRegNumber());
        typeTextBase(inputPrice, car.getPrice());
        typeTextBase(inputAbout, car.getAbout());
        addPictureToNewCarForm();

        clickBase(btnSubmit);

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
        return isTextEqual(getLocatorPopUp(manuf, model), manuf+" "+model+ " added successful");
    }

    public boolean validationPhotoAddedSuccess() {
        return isElementExist(textCarPhoto);
    }


    public void clickBtnNewForm() {
        clickBase(btnLetTheCarWork);
    }
}
