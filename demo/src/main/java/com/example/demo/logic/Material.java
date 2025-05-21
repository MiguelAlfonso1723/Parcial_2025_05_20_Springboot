package com.example.demo.logic;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private Integer publicationYear;

    @Column(nullable = false)
    private boolean disponible;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "material")
    private List<Loan> loans;

    public Material(Integer id, String title, String autor, Integer publicationYear, boolean disponible, String description, String type) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.publicationYear = publicationYear;
        this.disponible = disponible;
        this.description = description;
        this.type = type;
    }

    public Material() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
