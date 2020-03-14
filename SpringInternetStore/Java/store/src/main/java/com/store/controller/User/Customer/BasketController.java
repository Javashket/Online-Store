package com.store.controller.User.Customer;

import com.store.model.ProductInBasket;
import com.store.model.User;
import com.store.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('CUSTOMER')")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/basket")
    public String getBasket(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("balance", user.getBalance() != null ? user.getBalance(): "0");
        model.addAttribute("sum", basketService.getTotalBasketSum(user.getBasket().getId()));
        Integer basketId = user.getBasket().getId();
        Set<ProductInBasket> productInBasket = basketService.findBasketById(basketId).getProducts();
        if(!productInBasket.isEmpty()) {
            model.addAttribute("products", productInBasket);
        }
        return "/customer/basket";
    }

    @PostMapping("/add_product_basket{code}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addProductInBasket(@PathVariable String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        basketService.addProduct(user, Integer.parseInt(code));
    }

    @PostMapping("/delete_product_position_basket{code}")
    @ResponseBody
    public String deleteProductPositionInBasket (@PathVariable String code , Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        basketService.deleteProduct(user.getBasket().getId(), Integer.parseInt(code));
        return "ok";
    }

    @PostMapping("/dec_count_product{code}")
    @ResponseBody
    public String decrimentCountProduct(@PathVariable String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String count = String.valueOf(basketService.decrement(Integer.parseInt(code)));
        return getJsonForDecORInc(user, code, count);
    }

    @PostMapping("/inc_count_product{code}")
    @ResponseBody
    public String incrementCountProduct(@PathVariable String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String count = String.valueOf(basketService.increment(Integer.parseInt(code)));
       return getJsonForDecORInc(user, code, count);
    }

    private String getJsonForDecORInc(User user, String code, String count) {
        String totalPriceProduct = String.valueOf(basketService.getProductById(Integer.parseInt(code)).getProductTotalPrice());
        String totalBasketSum = String.valueOf(basketService.getTotalBasketSum(user.getBasket().getId()));
        return "{\"count\":\"" + count + "\"," + "\"price\":\"" + totalPriceProduct + "\"," +
                "\"sum\":\"" + totalBasketSum +"\"}";
    }

    @PostMapping("/pay")
    public String pay(Model model, Map <String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        List<String> checkResult = basketService.checkBeforePay(user);
        if(checkResult.size() > 0) {
            System.out.println(checkResult);
            model.addAttribute("errors",checkResult);
            model.addAttribute("login", user.getLogin());
            model.addAttribute("balance", user.getBalance() != null ? user.getBalance(): "0");
            model.addAttribute("sum", basketService.getTotalBasketSum(user.getBasket().getId()));
            Set<ProductInBasket> productInBasket = basketService.findBasketById(user.getBasket().getId()).getProducts();
            if(!productInBasket.isEmpty()) {
                model2.put("products", productInBasket);
            }
            return "/customer/basket";
        } else {
            basketService.pay(user);
            model.addAttribute("login", user.getLogin());
            model.addAttribute("balance", user.getBalance() != null ? user.getBalance() : "0");
            model.addAttribute("sum", basketService.getTotalBasketSum(user.getBasket().getId()));
            model.addAttribute("succesPay", "Заказ успешно совершен");
            return "/customer/basket";
        }
    }
}
