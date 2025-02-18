package factories;

import com.github.javafaker.Faker;
import helpers.DataSaver;
import models.Address;

public class AddressFactory {

    public static Address createRandomAddress() {
        Faker faker = new Faker();
        Address.AddressBuilder address = Address.builder();
        address
                .addressFirstLine(faker.address().streetAddress())
                .city(faker.address().city())
                .state(faker.address().state())
                .zipCode(String.valueOf(faker.random().nextInt(10000, 99999)));
        DataSaver.saveAddressToFile(address.build());
        return address.build();
    }
}
