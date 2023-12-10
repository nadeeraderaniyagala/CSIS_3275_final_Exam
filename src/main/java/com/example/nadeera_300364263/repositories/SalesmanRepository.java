package com.example.nadeera_300364263.repositories;

import com.example.nadeera_300364263.models.AggregatedSales;
import com.example.nadeera_300364263.models.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, Long> {


}
