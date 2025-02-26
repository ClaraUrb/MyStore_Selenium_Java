package factories;

import com.github.javafaker.Faker;
import helpers.DataSaver;
import models.User;

import static helpers.StringUtils.getRandomBirthdateFormatted;

public class UserFactory {

    public static User createRandomUser() {
        Faker faker = new Faker();
        User.UserBuilder user = User.builder();
        user
                .socialTitle(faker.options().option("Mr", "Mrs"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .birthdate(getRandomBirthdateFormatted());
        DataSaver.saveUserDataToFile(user.build());
        return user.build();
    }
}
