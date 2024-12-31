package userGenerator;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserGenerator {
    Random random = new Random();
    Faker faker = new Faker();

    public HashMap<String, String> generateUser() {
        HashMap<String, String> userData = new HashMap<>();

        userData.put("socialTitle", generateSocialTitle());
        userData.put("firstName", faker.name().firstName());
        userData.put("lastName", faker.name().lastName());
        userData.put("email", faker.internet().emailAddress());
        userData.put("password", faker.internet().password());
        userData.put("birthdate", generateBirthdate());
        return userData;
    }
    
    private String generateSocialTitle() {
        List<String> socialTitles = Arrays.asList("Mr", "Mrs");
        return socialTitles.get(random.nextInt(socialTitles.size() - 1));
    }

    private String generateBirthdate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(faker.date().birthday());
    }
}
