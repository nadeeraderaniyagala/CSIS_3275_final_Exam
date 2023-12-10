package com.example.nadeera_300364263.services;

import com.example.nadeera_300364263.models.AggregatedSales;
import com.example.nadeera_300364263.models.Salesman;
import com.example.nadeera_300364263.repositories.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesmanRepository salesmanRepository;

    public List<Salesman> getSales(){
        return salesmanRepository.findAll();
    }

    public Salesman saveSale(Salesman salesman){
        return salesmanRepository.save(salesman);
    }

    public Salesman getSaleById(Long id){
        Optional<Salesman> optionalSales = salesmanRepository.findById(id);
        return optionalSales.orElse(null);
    }

    public void deleteSaleById(Long id){
        salesmanRepository.deleteById(id);
    }

    // this method supports the sales summary table
    public List<AggregatedSales> getAggregatedSales(List<Salesman> salesmanList) {
        List<AggregatedSales> aggregatedSales = new ArrayList<>();

        if(Objects.nonNull(salesmanList)){
            for (Salesman salesman : salesmanList) {
                AggregatedSales aggregatedSale = aggregatedSales.stream()
                        .filter(a -> a.getName().equals(salesman.getName()))
                        .findFirst()
                        .orElseGet(() -> {
                            AggregatedSales newAggregatedSale = new AggregatedSales();
                            newAggregatedSale.setName(salesman.getName());
                            aggregatedSales.add(newAggregatedSale);
                            return newAggregatedSale;
                        });

                switch (salesman.getItem()) {
                    case "Washing Machine":
                        aggregatedSale.setWashingMachine(aggregatedSale.getWashingMachine() + salesman.getAmount());
                        break;
                    case "Refrigerator":
                        aggregatedSale.setRefrigerator(aggregatedSale.getRefrigerator() + salesman.getAmount());
                        break;
                    case "Music System":
                        aggregatedSale.setMusicSystem(aggregatedSale.getMusicSystem() + salesman.getAmount());
                        break;
                }
            }
        }

        return aggregatedSales;
    }

}
