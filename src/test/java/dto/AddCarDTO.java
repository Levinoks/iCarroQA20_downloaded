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
    private String year;
    private String fuel;
    private String seats;
    private String carClass;
    private String regNumber;
    private String price;
    private String about;
}
