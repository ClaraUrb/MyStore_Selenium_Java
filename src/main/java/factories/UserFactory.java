package factories;

import com.github.javafaker.Faker;
import models.User;

import static helpers.BirthdateFormatter.getRandomBirthdateFormatted;

public class UserFactory {

    public static User createRandomUser() {
        Faker faker = new Faker();
        User user = new User();
        user.setSocialTitle(faker.options().option("Mr", "Mrs"));
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setBirthdate(getRandomBirthdateFormatted());
        return user;
    }
}
