package data;

import dto.AddCarDTO;
import org.testng.annotations.DataProvider;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataProviderAddNewCar {
    RandomUtils randomUtils = new RandomUtils();

    @DataProvider
    public Iterator<Object[]> negativeYearAddNewCar() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("BMW")
                        .model("AZ-234")
                        .year(2024)
                        .seats(2)
                        .carClass("1")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(178)
                        .about("")
                        .build()
        });
        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("Suzuki")
                        .model("AZ-204")
                        .year(-1)
                        .seats(0)
                        .carClass("2")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(0)
                        .about("")
                        .build()
        });

return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> negativeSeatsAddNewCar() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("BMW")
                        .model("AZ-234")
                        .year(2012)
                        .seats(-1)
                        .carClass("1")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(178)
                        .about("")
                        .build()
        });
        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("Suzuki")
                        .model("AZ-204")
                        .year(1990)
                        .seats(0)
                        .carClass("2")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(84)
                        .about("")
                        .build()
        });
        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("Suzuki")
                        .model("AZ-204")
                        .year(1990)
                        .seats(1)
                        .carClass("2")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(80)
                        .about("")
                        .build()
        });
        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("Suzuki")
                        .model("AZ-204")
                        .year(1990)
                        .seats(21)
                        .carClass("2")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(67)
                        .about("")
                        .build()
        });
return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> negativePriceAddNewCar() {
        List<Object[]> list = new ArrayList<>();


        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("Suzuki")
                        .model("AZ-204")
                        .year(2005)
                        .seats(4)
                        .carClass("2")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(1001)
                        .about("")
                        .build()
        });
        list.add(new Object[]{
                AddCarDTO.builder()
                        .location("Haifa, Israel")
                        .manufacture("Suzuki")
                        .model("AZ-204")
                        .year(1998)
                        .seats(4)
                        .carClass("2")
                        .regNumber(randomUtils.generateDigitsString(10))
                        .price(-1)
                        .about("")
                        .build()
        });

return list.iterator();
    }

}
