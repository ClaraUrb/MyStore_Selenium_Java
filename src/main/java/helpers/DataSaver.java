package helpers;

import models.User;

import java.io.*;

public class DataSaver {
    private static String fileName = "user.dat";
    public static void saveUserDataToFile(User user) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.writeObject(user);
            output.close();
        } catch (IOException e) {
            System.err.println("Error when saving user data to file");
        }
    }
    public static User readUserDataFromFile() {
        User user = null;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
            user = (User) input.readObject();
            input.close();
        } catch (IOException e) {
            System.err.println("Error when opening file with user data");
        } catch (ClassNotFoundException e) {
            System.err.println("Reading user data from file failed");
        }
        return user;
    }
}
