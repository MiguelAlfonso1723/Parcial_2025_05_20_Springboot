package com.example.demo.repository;

import com.example.demo.logic.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {

    @Query("SELECT l FROM Loan l WHERE l.state = 'RETRASED'")
    public List<Loan> getRetrasedLoans();



    @Query("SELECT l FROM Loan l WHERE l.state = 'DELIVERED'")
    public List<Loan> getDeliveredLoans();



}
