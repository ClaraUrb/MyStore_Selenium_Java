package models;

import helpers.DataSaver;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Address implements Serializable {
    public String addressFirstLine;
    public String city;
    public String state;
    public String zipCode;

    @Override
    public String toString() {
        User user = DataSaver.readUserDataFromFile();
        return user.getFirstName() + " " + user.getLastName() + "\n" +
                addressFirstLine + '\n' +
                city + ", " + state + " " + zipCode + '\n' +
                "United States";
    }
}
