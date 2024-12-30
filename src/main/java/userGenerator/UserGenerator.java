package userGenerator;

import java.util.*;

public class UserGenerator {
    Random random = new Random();

    public HashMap<String, String> generateUser() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("socialTitle", generateSocialTitle());
        if (userData.get("socialTitle").equals("Mrs")) {
            userData.put("firstName", generateFirstNameFemale());
        } else {
            userData.put("firstName", generateFirstNameMale());
        }
        userData.put("lastName", generateLastName());
        userData.put("email", generateEmail());
        userData.put("birthdate", generateBirthdate());
        return userData;
    }
    
    private String generateSocialTitle() {
        List<String> socialTitles = Arrays.asList("Mr", "Mrs");
        return socialTitles.get(random.nextInt(socialTitles.size() - 1));
    }

    private String generateFirstNameFemale() {
        List<String> firstNames = Arrays.asList("Lana", "Mary", "Cristal", "Susan", "Nina");
        return firstNames.get(random.nextInt(firstNames.size() - 1));
    }
    private String generateFirstNameMale() {
        List<String> firstNames = Arrays.asList("Christian", "Clark", "Robert", "Frank", "Norm");
        return firstNames.get(random.nextInt(firstNames.size() - 1));
    }

    private String generateLastName() {
        List<String> firstNames = Arrays.asList("King", "Johnson", "Smith", "Moss", "Newman");
        return firstNames.get(random.nextInt(firstNames.size() - 1));
    }

    private String generateEmail() {
        return "randomEmail" + random.nextInt(9999) + "@email.com";
    }

    private String generateBirthdate() {
        List<String> birthdays = Arrays.asList("05/31/1970", "10/01/1975", "12/31/1989", "07/13/1995", "03/23/2002");
        return birthdays.get(random.nextInt(birthdays.size() - 1));
    }
}
