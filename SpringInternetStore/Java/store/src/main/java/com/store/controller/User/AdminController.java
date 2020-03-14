package com.store.controller.User;

import com.store.model.User;
import com.store.service.OrderService;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String getIndexAdmin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login",user.getLogin());
        return "/admin/admin";
    }

    @GetMapping("/order_list")
    public String getOrderList(Model model, Map<String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login",user.getLogin());
        model2.put("orders", orderService.getAllOrders());
        return "/admin/order_list";
    }

    @GetMapping("/user_list")
    public String getUserList(Model model,Map<String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login",user.getLogin());
        model2.put("users", userService.getAllUsers());
        return "/admin/user_list";
    }

    @GetMapping("/delete_user{code}")
    public String deleteUser(Model model,  @PathVariable String code,
                             Map<String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login",user.getLogin());
        boolean answer =  userService.deleteUserById(Integer.parseInt(code));
        if(!answer) {
            model.addAttribute("no_such_user", answer);
        }
        model2.put("users", userService.getAllUsers());
        return "/admin/user_list";
    }

    @GetMapping("/edit_user{code}")
    public String getEditUserPage(Model model,  @PathVariable String code,
                             Map<String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login",user.getLogin());
        model.addAttribute("code", code);
        model.addAttribute("role",userService.getUserById(Integer.parseInt(code)).getRole());
        return "/admin/edit_user";
    }

    @PostMapping("/edit_user{code}")
    public String EditUser(Model model,  @PathVariable String code,
                           @RequestParam String login, @RequestParam String password,
                           @RequestParam(required = false) String balance,
                           Map<String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login",user.getLogin());
        model2.put("users", userService.getAllUsers());
        if(balance == null) {
            userService.editUserPasswordAndLogin(userService.getUserById(Integer.parseInt(code)),password,login);
        } else {
            userService.editUserPasswordAndLoginAndBalance(userService.getUserById(Integer.parseInt(code)),password,login,Integer.parseInt(balance));
        }
        return "/admin/user_list";
    }
}
