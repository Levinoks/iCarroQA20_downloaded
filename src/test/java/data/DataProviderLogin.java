package data;

import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderLogin {
    @DataProvider
    public Iterator<Object[]> positiveDataLogin(){
        List <Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("qwerty1@gmail.com")
                        .password("User#12345")
                        .build()
        });

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> negativePasswordDataLogin(){
        List <Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("qwerty1@gmail.com")
                        .password("User12345")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("qwerty1@gmail.com")
                        .password("user12345")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("qwerty1@gmail.com")
                        .password("12345678")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("qwerty1@gmail.com")
                        .password("Aaaabbbccc")
                        .build()
        });

        return list.iterator();
    }



    @DataProvider
    public Iterator<Object[]> loginCSV() {
        List<Object[]> list = new ArrayList<>();
        String path = "src//test//resources//usersreg.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line = reader.readLine();
            while(line != null){
                String[] split=line.split(",");
                list.add(new Object[]{
                        UserDtoLombok.builder()

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
