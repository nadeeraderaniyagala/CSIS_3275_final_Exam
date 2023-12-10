package com.example.nadeera_300364263.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="salesman")
public class Salesman {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "dot")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dot;
    @Column(name = "item")
    private String item;
    @Column(name = "name")
    private String name;
}
