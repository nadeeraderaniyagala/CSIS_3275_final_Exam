package com.example.nadeera_300364263.controllers;

import com.example.nadeera_300364263.models.AggregatedSales;
import com.example.nadeera_300364263.models.Salesman;
import com.example.nadeera_300364263.repositories.SalesmanRepository;
import com.example.nadeera_300364263.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {
        List<Salesman> salesmanList = salesService.getSales();
        model.addAttribute("sales", salesmanList);

        Salesman sales = new Salesman();//needed for saveSale method
        sales.setDot(LocalDate.now());
        model.addAttribute("sale", sales);

        List<AggregatedSales> aggregatedSalesList = salesService.getAggregatedSales(salesmanList);
        model.addAttribute("aggreSales", aggregatedSalesList);

        return "index";
    }

    // save sales
    @PostMapping("/sales")
    public String saveSale(@ModelAttribute("sale") Salesman salesman) {
        salesService.saveSale(salesman);
        return "redirect:/";
    }

    // edit -get
    @GetMapping("/sales/edit/{id}")
    public String editSalesForm(@PathVariable Long id, Model model) {
        model.addAttribute("sale", this.salesService.getSaleById(id));
        return "edit-sales";
    }

    // edit
    @PostMapping("/sales/edit/{id}")
    public String updateSales(@PathVariable Long id,
                              @ModelAttribute("sale") Salesman sale) {
        sale.setId(id);
        this.salesService.saveSale(sale);
        return "redirect:/";
    }

    //delete
    @GetMapping("/sales/{id}")
    public String deleteSale(@PathVariable Long id) {
        this.salesService.deleteSaleById(id);
        return "redirect:/";
    }


}
