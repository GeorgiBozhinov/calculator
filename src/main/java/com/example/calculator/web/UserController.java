package com.example.calculator.web;
import com.example.calculator.data.model.dto.UpdateUserDTO;
import com.example.calculator.data.service.UserRoleService;
import com.example.calculator.data.service.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserRoleService userRoleService;

    public UserController(UserService userService, UserRoleService userRoleService) {

        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/login")
    private String loginPage() {

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
    private String allUsers(Model model) {

        List<UpdateUserDTO> tes = userService.getAllUsers();

        model.addAttribute("users", userService.getAllUsers());

        return "views/manage_users";
    }

    @GetMapping("/edit/{id}")
    private String editUser(@PathVariable("id") long id, Model model) {

        UpdateUserDTO updateUserDTO = userService.getUser(id);
        //List<UserRoleEntity> listEntity = updateUserDTO.getUserRoles();

        // List<UserRoleEnum> listOfRoles = userRoleService.returnAllUserRoles();
//

        model.addAttribute("userModel", updateUserDTO);
        // model.addAttribute("listOfRoles", listOfRoles);

        return "views/update_user";
    }

    @PostMapping("/update/{id}")
    private String updateUser(@PathVariable("id") long id, @Valid UpdateUserDTO updateUserDTO, BindingResult bindingResult, Model model) {

        if ( bindingResult.hasErrors() ) {
            updateUserDTO.setId(id);

            return "views/update_user";
        }

        userService.updateUser(updateUserDTO);

        return "redirect:/users/all";
    }

    @GetMapping("/delete/{id}")
    private String deleteUser(@PathVariable("id") long id, Model model) {

//        if(bindingResult.hasErrors()){
//            updateUserDTO.setId(id);
//
//            return "views/update_user";
//        }

        userService.deleteUser(id);

        return "redirect:/users/all";
    }

}
