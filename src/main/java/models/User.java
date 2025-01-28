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
}

