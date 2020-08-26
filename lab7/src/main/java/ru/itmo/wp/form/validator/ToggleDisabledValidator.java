package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.ToggleDisabledForm;
import ru.itmo.wp.service.UserService;

@Controller
public class ToggleDisabledValidator implements Validator {
    private final UserService userService;

    public ToggleDisabledValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ToggleDisabledForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            ToggleDisabledForm disableForm = (ToggleDisabledForm) target;
            if (userService.findById(disableForm.getId()) == null) {
                errors.rejectValue("id", "id.invalid-id", "invalid id, try again");
            }
        }
    }
}
