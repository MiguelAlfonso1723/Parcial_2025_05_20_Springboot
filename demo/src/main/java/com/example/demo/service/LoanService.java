package com.example.demo.service;

import com.example.demo.logic.Loan;
import com.example.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> loanList(){
        return loanRepository.findAll();
    }

    public Loan getLoanById(Integer id){
        return loanRepository.findById(id).get();
    }

    public Loan addLoan(Loan loan){
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Loan loan, Integer id){
        if(loanRepository.findById(id).isPresent()){
            Loan auxLoan = loanRepository.findById(id).get();
            auxLoan.setLoanDate(loan.getLoanDate());
            auxLoan.setDevolutionDate(loan.getDevolutionDate());
            auxLoan.setState(loan.getState());
            auxLoan.setLimitDate(loan.getLimitDate());
            return loanRepository.save(auxLoan);

        }else{
            return null;
        }
    }

    public List<Loan> retrasedLoans(){
        return loanRepository.getRetrasedLoans();
    }

    public void actualizeState(){

        loanRepository.findAll().stream().filter(loan -> loan.getDevolutionDate() == null)
                .forEach(loan ->{
                    if (loan.getLimitDate().before(new Date())) {
                        if (!"RETRASED".equals(loan.getState())) {
                            loan.setState("RETRASED");
                            loanRepository.save(loan);
                        }
                    }
                });

    }

    public List<Loan> deliveredLoans(){
        return loanRepository.getDeliveredLoans();
    }

    public Loan deliver(Integer id){
        Loan auxLoan = loanRepository.findById(id).get();
        auxLoan.setState("DELIVERED");
        auxLoan.setDevolutionDate(new Date());
        return loanRepository.save(auxLoan);
    }
}
