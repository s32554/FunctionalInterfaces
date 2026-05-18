import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Exercise1UserValidation {

    // Record with a compact constructor to handle blank string validation
    public record UserForm(String email, String password, int age) {
        public UserForm {
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email cannot be blank.");
            }
            if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("Password cannot be blank.");
            }
        }
    }

    // Validator class managing the evaluation rules
    public static class UserValidator {
        private final List<Predicate<UserForm>> rules = new ArrayList<>();

        public void addRule(Predicate<UserForm> rule) {
            rules.add(rule);
        }

        public boolean isValid(UserForm form) {
            return rules.stream().allMatch(rule -> rule.test(form));
        }
    }

    public static void main(String[] args) {
        UserValidator validator = new UserValidator();

        // Adding rules as lambdas
        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);

        // Test Cases
        try {
            UserForm validForm = new UserForm("user@example.com", "securePass123", 25);
            UserForm invalidForm = new UserForm("bademail.com", "short", 16);

            System.out.println("Is validForm valid? " + validator.isValid(validForm));     // True
            System.out.println("Is invalidForm valid? " + validator.isValid(invalidForm)); // False
            
            // This will trigger the compact constructor validation exception:
            UserForm brokenForm = new UserForm("", "password", 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Validation Error: " + e.getMessage());
        }
    }
}