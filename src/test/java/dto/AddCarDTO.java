package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AddCarDTO {
    private String location;
    private String manufacture;
    private String model;
    private int year;
    private String fuel;
    private int seats;
    private String carClass;
    private String regNumber;
    private int price;
    private String about;
}
