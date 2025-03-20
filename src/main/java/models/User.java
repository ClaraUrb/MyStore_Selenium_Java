package models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {
    public String socialTitle;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String birthdate;

    @Override
    public String toString() {
        return "User{" +
                "socialTitle='" + socialTitle + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}

