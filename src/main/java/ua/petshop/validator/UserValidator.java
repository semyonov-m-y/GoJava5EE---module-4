package ua.petshop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ua.petshop.dto.UserDto;
import ua.petshop.service.UserService;

@Component
public class UserValidator {

    public void validate(UserDto user, Errors errors, UserService userService) {
        if (user != null) {
            if (userService.findByName(user.getName())!= null) {
                errors.rejectValue("name",
                    "user.exists",
                    "A user with such name already exists");
            }
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword",
                    "passwords.do.not.match",
                    "Passwords do not match");
            }
        }
    }
}
