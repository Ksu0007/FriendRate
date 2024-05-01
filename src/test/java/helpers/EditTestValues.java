package helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EditTestValues {
    private static final Random random = new Random();
    public static final String PROFILE_URL = "https://friend-rate-front.vercel.app/en/profile";


    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        List<Character> chars = new ArrayList<>();
        for (char c : characters.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.get(random.nextInt(chars.size())));
        }
        return sb.toString();
    }

    //Username

    public static String validMinUsername() {
        return generateRandomString(3);
    }

    public static String validMaxUsername() {
        return generateRandomString(25);
    }

    public static String invalidMinUsername() {
        return generateRandomString(2);
    }

    public static String invalidMaxUsername() {
        return generateRandomString(26);
    }
    public static  String validUsernameUnderscore() {
        return generateRandomString(5) + "_" +generateRandomString(3);
    }
    public static  String validUsernameDot() {
        return generateRandomString(5) + "." +generateRandomString(3);
    }

    public static  String validUsernameAt() {
        return generateRandomString(5) + "@" +generateRandomString(3);
    }

    public static  String validUsernamePlus() {
        return generateRandomString(5) + "+" +generateRandomString(3);
    }

    public static  String validUsernameHyphen() {
        return generateRandomString(5) + "_" +generateRandomString(3);
    }

    public static  String invalidUsernameSymbol() {
        return generateRandomString(5) + "#" +generateRandomString(3);
    }

    //Email
    public static final String EMPTY_LOCAL_PART = "@example.com";


    // Min length - 5 / Max length - 60
    public static String emailValidMaxLength() {
        return generateRandomString(38) + "@example.com";
    }
    public static String emailInvalidMaxLength() {
        return generateRandomString(49) + "@example.com";
    }

    public static String emailInvalidLocalPartSymbols() {
        return generateRandomString(3) + "!" + generateRandomString(2) + "@example.com";
    }


    // without symbol "@"
    public static String emailWithoutAtSymbol() {
        return generateRandomString(10) + "example.com";
    }


    // The local part (before "@"): May contain a dot
    public static String emailWithDotInLocalPart() {
        return generateRandomString(2) + "." + generateRandomString(3) + "@example.com";
    }

    public static String emailWithoutDotInDomainPart() {
        return generateRandomString(7)  + "@examplecom";
    }

    public static String emailWithSymbolInDomainPart() {
        return generateRandomString(5) + "@ex!mple.com";
    }

    //Birthday

    public static final String VALID_MAX_DATE = "01.01.1925";
    public static final String VALID_MIN_DATE = "31.12.2023";
    public static final String INVALID_DAY = "30.02.2000";
    public static final String INVALID_MONTH = "01.13.1988";
    public static final String INVALID_YEAR_PAST = "12.12.1924";
    public static final String INVALID_YEAR_FUTURE = "01.01.2024";

    //Password

    public static final String INVALID_SHORT_PASSWORD = "Short1$";
    public static final String VALID_PASSWORD_8 = "Abc12345!";
    public static final String VALID_PASSWORD_29 = "Abc1234567890123456789012345!";
    public static final String MAX_LENGTH_PASSWORD = "Abc12345678901234567890123456!";
    public static final String INVALID_LONG_PASSWORD = "Abc1234567890123456789012345678!";
    public static final String PASSWORD_WITHOUT_LETTERS = "1234567890$";
    public static final String PASSWORD_WITHOUT_NUMBERS = "Password$";
    public static final String PASSWORD_WITHOUT_SPECIAL_CHARACTERS = "Password123";






    //Error Messages

    public static final String ERROR_INVALID_USERNAME = "Username must be 3-25 characters and combination of " +
            "latin letters, numbers, and special symbols.";

    public static final String ERROR_EMPTY_EMAIL = "Required";
    public static final String ERROR_INVALID_EMAIL = "Please enter a valid email address.";
    public static final String ERROR_LONG_EMAIL = "Email should not exceed 50 characters";

    public static final String ERROR_INVALID_BIRTHDAY = "Invalid date format: Birthdate must be written" +
            " in 'DD.MM.YYYY' format.";

    public static final String ERROR_INVALID_PASSWORD = "Password must be 8-30 characters and a combination of numbers," +
            " letters and special symbols.";

    public static final String ERROR_CONFIRM_PASSWORD = "Passwords do not match. Please re-enter your password.";
    public static final String ERROR_SHORT_ABOUT = "This field must contain at least 10 characters";
    public static final String ERROR_LONG_ABOUT = "This field must contain less than 255 characters";






}
