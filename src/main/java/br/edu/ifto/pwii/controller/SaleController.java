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

import java.util.List;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final ModelMapper mapper;

    @GetMapping
    public String getAllPage(Model model) {
        List<Sale> salesList = saleService.findAll();
        var salesDto = salesList.stream().map(
                sale -> {
                    var dto = new SaleDTO();
                    dto.setId(sale.getId());
                    dto.setTotalPrice(sale.totalPrice());
                    dto.setUser(mapper.map(sale.getUser(), UserDTO.class));
                    dto.setItems(sale.getItems());
                    dto.setCreatedAt(sale.getCreatedAt());
                    dto.setUpdatedAt(sale.getUpdatedAt());
                    dto.setDeletedAt(sale.getDeletedAt());
                    return dto;
                }
        ).toList();

        model.addAttribute("title", "Sales");
        model.addAttribute("sales", salesDto);
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
