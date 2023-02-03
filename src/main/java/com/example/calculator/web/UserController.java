package com.example.calculator.web;

import com.example.calculator.data.model.dto.UpdateUserDTO;
import com.example.calculator.data.service.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    private String loginPage(){
        return "auth/login";
    }

    // NOTE: This should be post mapping!
    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        redirectAttributes.addFlashAttribute("bad_credentials",
                true);

        return "redirect:/users/login";
    }

    @GetMapping("/all")
    private String allUsers(Model model){

        List<UpdateUserDTO> tes = userService.getAllUsers();

        model.addAttribute("users", userService.getAllUsers());

        return "views/manage_users";
    }

    @GetMapping("/edit/{id}")
    private String editUser(@PathVariable("id") long id,  Model model){

        Optional<UpdateUserDTO> updateUserDTO = userService.getUser(id);

//        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
//        updateUserDTO.setUsername("test");
//        updateUserDTO.setFirstName("test");
//        updateUserDTO.setLastName("test");

        model.addAttribute("userModel", updateUserDTO);

        return "views/update_user";
    }


}
