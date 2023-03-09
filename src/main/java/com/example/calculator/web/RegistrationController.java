package com.example.calculator.web;
import com.example.calculator.data.model.dto.UserRegisterDTO;
import com.example.calculator.data.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.calculator.configs.StringConstants.PASSWORDS_DOES_NOT_MATCH;
import static com.example.calculator.configs.StringConstants.USER_EXIST;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {

        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserRegisterDTO userRegisterDTO() {

        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    private String registerPage() {

        return "auth/register";
    }

    @PostMapping("/register")
    private String register(@Valid UserRegisterDTO userRegisterDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        if ( userService.createUserIfNotExist(userRegisterDTO.getUsername()) ) {
            ObjectError error = new ObjectError("globalError", USER_EXIST);
            bindingResult.addError(error);

            redirectAttributes.addFlashAttribute("userModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);

            return "redirect:/users/register";
        }

        System.out.println("pass: " + userRegisterDTO.getPassword());
        System.out.println("conf-pass: " + userRegisterDTO.getConfirmPassword());

        if ( !userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword()) ) {
            ObjectError error = new ObjectError("globalError", PASSWORDS_DOES_NOT_MATCH);
            bindingResult.addError(error);

            redirectAttributes.addFlashAttribute("userModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);

            return "redirect:/users/register";
        }

        if ( bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);

            return "redirect:/users/register";
        }

        userService.registerAndLogin(userRegisterDTO);

        return "redirect:/home";
    }

}
