package com.example.nadeera_300364263.controllers;

import com.example.nadeera_300364263.models.AggregatedSales;
import com.example.nadeera_300364263.models.Salesman;
import com.example.nadeera_300364263.services.SalesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SalesControllerTest {
    Salesman sales;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SalesService salesService;

    @InjectMocks
    private SalesController salesController;

    Salesman salesman;
    AggregatedSales aggregatedSales;

    // set up test data before each test
    @BeforeEach
    void setUp() {
        salesman = new Salesman();
        salesman.setId(1L);
        salesman.setName("Test");
        salesman.setItem("Refrigerator");
        salesman.setAmount(20.0);
        salesman.setDot(LocalDate.now());

        aggregatedSales = new AggregatedSales();
        aggregatedSales.setName("TEST-AGGREGATED");
        aggregatedSales.setRefrigerator(1.0);
        aggregatedSales.setMusicSystem(1.0);
        aggregatedSales.setWashingMachine(1.0);


        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(salesController)
                .build();
    }

    //displaying records
    @Test
    void getIndexPage()throws Exception {
        List<Salesman> salesList = new ArrayList<>();
        salesList.add(sales);

        List<AggregatedSales> aggregatedSalesList = new ArrayList<>();
        aggregatedSalesList.add(aggregatedSales);

        when(salesService.getSales()).thenReturn(salesList);
        when(salesService.getAggregatedSales(salesList)).thenReturn(aggregatedSalesList);

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("sales", salesList))
                .andExpect(model().attribute("aggreSales", aggregatedSalesList));
        verify(salesService, times(1)).getSales();
    }


    //adding a record
    @Test
    void saveSale() throws  Exception {
        List<Salesman> salesList = new ArrayList<>();
        salesList.add(sales);
        when(salesService.getSales()).thenReturn(salesList);

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("sales", salesList));

        verify(salesService, times(1)).getSales();
    }

    // deleting a record
    @Test
    void deleteSale() {
        //arrange
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        //act
        doNothing().when(salesService).deleteSaleById(idCapture.capture());
        salesService.deleteSaleById(1L);
        //assert
        assertEquals(1L, idCapture.getValue());
        verify(salesService, times(1)).deleteSaleById(1L);
    }
}