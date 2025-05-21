package com.example.demo.logic;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Material material;

    @Column (nullable = false)
    private Date loanDate;

    @Column (nullable = false)
    private Date limitDate;

    @Column
    private Date devolutionDate;

    @Column
    private String state;

    public Loan(Integer id, Date loanDate, Date limitDate, Date devolutionDate, String state) {
        this.id = id;
        this.loanDate = loanDate;
        this.limitDate = limitDate;
        this.devolutionDate = devolutionDate;
        this.state = state;
    }

    public Loan() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
