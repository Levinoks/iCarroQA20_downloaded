package data;

import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;
import utils.RandomUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderRegistration {
    RandomUtils random=new RandomUtils();
    @DataProvider
    public Iterator<Object[]> positiveDataRegistration(){
        List <Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .lastName("tester")
                        .name("test")
                        .email(random.generateEmail(5))
                        .password("User#12345")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .lastName("student")
                        .name("tester")
                        .email(random.generateEmail(5))
                        .password(random.generatePassword(8))
                        .build()
        });

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> negativePasswordDataRegistration(){
        List <Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email(random.generateEmail(6))
                        .password("User12345")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email(random.generateEmail(6))
                        .password("user12345")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email(random.generateEmail(6))
                        .password("12345678")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email(random.generateEmail(6))
                        .password("Aaaabbbccc")
                        .build()
        });

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> regCSV() {
        List<Object[]> list = new ArrayList<>();
        String path = "src//test//resources//usersreg.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line = reader.readLine();
            while(line != null){
                String[] split=line.split(",");
                list.add(new Object[]{
                        UserDtoLombok.builder()
                                .name(split[0])
                                .lastName(split[1])
                                .email(split[2])
                                .password(split[3])
                                .build()
                });
                line= reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();

    }

}

