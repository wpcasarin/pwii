package br.edu.ifto.pwii.controller;

import br.edu.ifto.pwii.model.Product;
import br.edu.ifto.pwii.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllPage(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("title", "Products");
        model.addAttribute("products", productList);
        model.addAttribute("product", new Product());
        return "views/product";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/products";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("product") Product product) {
        var existingProduct = productService.findById(id).orElseThrow(() -> new RuntimeException("Invalid product ID: " + id));
        productService.update(id, existingProduct, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/products";
    }

}
