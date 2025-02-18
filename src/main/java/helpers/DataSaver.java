package helpers;

import models.Address;
import models.User;

import java.io.*;

public class DataSaver {
    private static final String userDataFileName = "user.dat";
    private static final String addressFileName = "address.dat";

    public static void saveUserDataToFile(User user) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(userDataFileName));
            output.writeObject(user);
            output.close();
        } catch (IOException e) {
            System.err.println("Error when saving user data to file");
        }
    }

    public static User readUserDataFromFile() {
        User user = null;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(userDataFileName));
            user = (User) input.readObject();
            input.close();
        } catch (IOException e) {
            System.err.println("Error when opening file with user data");
        } catch (ClassNotFoundException e) {
            System.err.println("Reading user data from file failed");
        }
        return user;
    }

    public static void saveAddressToFile(Address address) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(addressFileName));
            output.writeObject(address);
            output.close();
        } catch (IOException e) {
            System.err.println("Error when saving address to file");
        }
    }

    public static Address readAddressFromFile() {
        Address address = null;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(addressFileName));
            address = (Address) input.readObject();
            input.close();
        } catch (IOException e) {
            System.err.println("Error when opening file with address");
        } catch (ClassNotFoundException e) {
            System.err.println("Reading address from file failed");
        }
        return address;
    }
}
