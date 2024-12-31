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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(faker.date().birthday());
    }
}
