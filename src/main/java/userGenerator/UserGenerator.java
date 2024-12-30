package userGenerator;

import com.github.javafaker.Faker;

import java.util.*;

public class UserGenerator {
    Random random = new Random();

    public HashMap<String, String> generateUser() {
        HashMap<String, String> userData = new HashMap<>();
        Faker faker = new Faker();
        userData.put("socialTitle", generateSocialTitle());
        userData.put("firstName", faker.name().firstName());
        userData.put("lastName", faker.name().lastName());
        userData.put("email", generateEmail());
        userData.put("birthdate", generateBirthdate());
        return userData;
    }
    
    private String generateSocialTitle() {
        List<String> socialTitles = Arrays.asList("Mr", "Mrs");
        return socialTitles.get(random.nextInt(socialTitles.size() - 1));
    }

    private String generateEmail() {
        return "randomEmail" + random.nextInt(9999) + "@email.com";
    }

    private String generateBirthdate() {
        List<String> birthdays = Arrays.asList("05/31/1970", "10/01/1975", "12/31/1989", "07/13/1995", "03/23/2002");
        return birthdays.get(random.nextInt(birthdays.size() - 1));
    }
}
