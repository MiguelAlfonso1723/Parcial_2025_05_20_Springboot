package com.example.demo.repository;

import com.example.demo.dtos.MaterialDTO;
import com.example.demo.logic.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {
    @Query("SELECT m FROM Material m WHERE m.title LIKE CONCAT('%',:title,'%') ")
    public List<Material> findByName(String title);

    @Query("SELECT new com.example.demo.dtos.MaterialDTO(title, autor, type, disponible) FROM Material")
    public List<MaterialDTO> getFieldsMaterials();

    @Query("SELECT m FROM Material m WHERE m.title = :title")
    public Material getMaterialByName(String title);
}
