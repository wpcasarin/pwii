package br.edu.ifto.pwii.controller;

import br.edu.ifto.pwii.dto.SaleDTO;
import br.edu.ifto.pwii.dto.UserDTO;
import br.edu.ifto.pwii.model.Sale;
import br.edu.ifto.pwii.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final ModelMapper mapper;

    @GetMapping
    public String getAllPage(Model model) {

        var salesList = saleService
                .findAll()
                .stream()
                .map(sale -> {
                    SaleDTO dto = mapper.map(sale, SaleDTO.class);
                    dto.setUser(mapper.map(sale.getUser(), UserDTO.class));
                    return dto;
                }).toList();

        model.addAttribute("title", "Sales");
        model.addAttribute("sales", salesList);
        model.addAttribute("sale", new Sale());
        return "views/sale";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("sale") Sale sale) {
        saleService.create(sale);
        return "redirect:/sales";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("sale") Sale sale) {
        var existingSale = saleService.findById(id).orElseThrow(() -> new RuntimeException("Invalid sale ID: " + id));
        saleService.update(id, existingSale, sale);
        return "redirect:/sales";
    }

    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {
        saleService.delete(id);
        return "redirect:/sales";
    }

}
