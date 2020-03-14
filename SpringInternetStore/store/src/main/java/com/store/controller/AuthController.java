package com.store.controller;

import com.store.model.User;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @GetMapping("/recovery")
    public String recovery() {
        return "/auth/recovery";
    }

    @PostMapping("/recovery")
    public String sendRecoveryOnEmail(@RequestParam String email, Model model){
        if(!userService.recoveryUser(email)) {
            model.addAttribute("notFoundEmail",true);
            return "/auth/recovery";
        } else {
            model.addAttribute("successRegistration",true);
            return "/auth/login";
        }
    }

    @GetMapping("/recovery_pass/{code}")
    public String NewPassword (@PathVariable String code ,Model model) {
        model.addAttribute("code",code);
        return "/auth/recovery_pass";
    }

    @PostMapping("/recovery_pass/{code}")
    public String enterNewPassword (Model model, @PathVariable String code,
                                    @RequestParam  String password) {
        if (password.length() < 6 || password.length() > 15) {
            model.addAttribute("passwordError", "Пароль должен быть от 6 до 15 символов");
            model.addAttribute("code",code);
            return "/auth/recovery_pass";
        }
        User user = userService.searchByActivationCode(code);
        userService.editUserPassword(user,password);
        userService.activateUser(code);
        model.addAttribute("editPassword",true);
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult,
                          @RequestParam String confirm_password, Model model) {
        if (bindingResult.hasErrors()) {
            populateError("login", model, bindingResult);
            populateError("password", model, bindingResult);
            populateError("email", model, bindingResult);
            return "/auth/registration";
        }
        if(!user.getPassword().equals(confirm_password)){
            model.addAttribute("errorEqualsPassword", true);
            return "/auth/registration";
        }
       if (!userService.addUser(user)) {
           model.addAttribute("errorDublicateMail", true);
                   return "/auth/registration";
       }else {
           model.addAttribute("successRegistration", true);
       }
       return "/auth/login";
    }

    @GetMapping("/activate/{code}")
    public String activate (Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if(isActivated) {
            model.addAttribute("success",true);
        } else {
            model.addAttribute("errorCode", true);
        }
        return "/auth/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false)Boolean error,
                        Model model) {
        if(Boolean.TRUE.equals(error)){
            model.addAttribute("error",true);
        }
        return "/auth/login";
    }

    @GetMapping("/notactivate")
    public String notActivate() {
        return "/auth/notactivate";
    }

    @GetMapping("/redirect")
    public String lesson() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if (user.getActivationCode() == null || user.getActivationCode().equals("null")) {
            Collection<? extends GrantedAuthority> authorities;
            authorities = auth.getAuthorities();
            String myRole = authorities.toArray()[0].toString();
            if (myRole.equals("ADMIN")) {
                return "redirect:/admin";
            }
            if (myRole.equals("CUSTOMER")) {
                return "redirect:/customer";
            }
            return "redirect:/seller";
        } else  {
            return "redirect:/notactivate";
        }
    }

    private void populateError (String field, Model model, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            model.addAttribute(field + "Error", bindingResult.getFieldError(field)
                    .getDefaultMessage());
        }
    }
}
