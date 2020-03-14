package com.store.controller.User.Customer;

import com.store.model.Product;
import com.store.model.User;
import com.store.service.ListDesireService;
import com.store.service.OrderService;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('CUSTOMER')")
public class CustomerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ListDesireService listDesireService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/customer")
    public String getProductsIndexCustomer(@RequestParam(required = false) String gender,
                                           @RequestParam(required = false) String season,
                                           @RequestParam(required = false) String type, Model model,
                                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("balance", user.getBalance() != null ? user.getBalance(): "0");
        if(gender != null && season != null && type != null) {
            model.addAttribute("products", productService.getProductsByCategories(type,season,gender,pageable));
            model.addAttribute("url", "/customer?gender=" + gender + "&season=" + season + "&type=" + type + "&");
        } else {
            model.addAttribute("notCategory",true);
            model.addAttribute("url", "/customer");
        }
        model.addAttribute("gender", gender);
        model.addAttribute("type", type);
        model.addAttribute("season", season);
        return "/customer/customer";
    }

    @GetMapping("/search")
    public String searchByDescriptionOrName(@RequestParam(required = false) String text,
                                            @RequestParam(required = false) String search,Model model,
                                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("balance", user.getBalance() != null ? user.getBalance(): "0");
        if(search != null && text != null && !text.equals("")) {
            model.addAttribute("products", productService.findByNameOrDescription(text, search, pageable));
            model.addAttribute("url", "/search?search=" + search + "&text=" + text + "&");
            model.addAttribute("search", search);
            model.addAttribute("text", text);
        } else {
            model.addAttribute("url", "/search");
        }
        return "/customer/search_for_customer";
    }

    @GetMapping("/product_customer{code}")
    public String search(@PathVariable String code, Model model, Map<String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("balance", user.getBalance() != null ? user.getBalance(): "0");
        model2.put("product", productService.findById(Integer.parseInt(code)));
        return "/customer/product_customer";
    }

    @GetMapping("/order_hystory")
    public String getOrderHystory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("balance", user.getBalance() != null ? user.getBalance(): "0");
        model.addAttribute("orderlists", orderService.getOrdersById(user));
        return "/customer/order_history";
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("balance", user.getBalance() != null ? user.getBalance(): "0");
        Set<Product> listDesires = listDesireService.findById(user.getListDesires().getId()).getProducts();
        if(!listDesires.isEmpty()) {
            model.addAttribute("products", listDesires);
        }
        return "/customer/favorites";
    }


    @PostMapping("/add_product_favorites{code}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addProductInListDesires(@PathVariable String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        listDesireService.addProduct(user, Integer.parseInt(code));
    }

    @PostMapping("/delete_product_favorites{code}")
    @ResponseBody
    public String deleteProductInListDesires(@PathVariable String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        listDesireService.deleteProduct(user, Integer.parseInt(code));
        return "ok";
    }
}
