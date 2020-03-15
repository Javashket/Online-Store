package com.store.controller.User;

import com.store.model.Product;
import com.store.model.User;
import com.store.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('SELLER')")
public class SellerController {

    private final ProductService productService;

    public SellerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/seller")
    public String getProductsIndexCustomer(@RequestParam(required = false) String gender,
                                           @RequestParam(required = false) String season,
                                           @RequestParam(required = false) String type, Model model,
                                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        if (gender != null && season != null && type != null) {
            model.addAttribute("products", productService.getProductsByCategories(type, season, gender, pageable));
            model.addAttribute("url", "/seller?gender=" + gender + "&season=" + season + "&type=" + type + "&");
        } else {
            model.addAttribute("notCategory", true);
            model.addAttribute("url", "/seller");
        }
        model.addAttribute("gender", gender);
        model.addAttribute("type", type);
        model.addAttribute("season", season);
        return "/seller/seller";
    }

    @GetMapping("/product_add")
    public String getPageAddProduct(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        return "/seller/product_add";
    }

    @PostMapping("/product_add")
    public String addProduct(@RequestParam String season, @RequestParam String type,
                             @RequestParam String gender, @RequestParam String description,
                             @RequestParam String name, @RequestParam String price,
                             @RequestParam String count, Model model) {
        Product product = new Product(name, price, count, description, type, season, gender);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        productService.addProduct(product);
        model.addAttribute("succesAddProduct", true);
        return "/seller/seller";
    }

    @GetMapping("/edit_product{code}")
    public String getPageEditProduct(Model model, @PathVariable String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("code", code);
        model.addAttribute("product", productService.findById(Integer.parseInt(code)));
        return "/seller/product_edit";
    }

    @PostMapping("/edit_product{code}")
    public String editProduct(@RequestParam String season, @RequestParam String type,
                              @RequestParam String gender, @RequestParam String description,
                              @RequestParam String name, @RequestParam String price,
                              @RequestParam String count, Model model,
                              @PathVariable String code) {
        productService.editProduct(Integer.parseInt(code), name, price, count, description, season, type, gender);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        return "redirect:/seller";
    }

    @GetMapping("/product_seller{code}")
    public String search(@PathVariable String code, Model model, Map<String, Object> model2) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        model2.put("product", productService.findById(Integer.parseInt(code)));
        return "/seller/product_seller";
    }

    @PostMapping("/delete_product{code}")
    @ResponseBody
    public String search(@PathVariable String code) {
        productService.deleteProduct(Integer.parseInt(code));
        return "ok";
    }

    @GetMapping("/search_for_seller")
    public String searchByDescriptionOrName(@RequestParam(required = false) String text,
                                            @RequestParam(required = false) String search, Model model,
                                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("login", user.getLogin());
        if (search != null && text != null && !text.equals("")) {
            model.addAttribute("products", productService.findByNameOrDescription(text, search, pageable));
            model.addAttribute("url", "/search_for_seller?search=" + search + "&text=" + text + "&");
            model.addAttribute("search", search);
            model.addAttribute("text", text);
        } else {
            model.addAttribute("url", "/search_for_seller");
        }
        return "/seller/search_for_seller";
    }
}
