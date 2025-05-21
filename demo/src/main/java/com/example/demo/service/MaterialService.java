package com.example.demo.service;

import com.example.demo.dtos.MaterialDTO;
import com.example.demo.logic.Material;
import com.example.demo.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> getMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterialById(Integer id) {
        return materialRepository.findById(id).get();
    }


    public Material addMaterial(Material material){
        return materialRepository.save(material);
    }

    public Material deleteMaterial(Integer id){
        Material product = materialRepository.findById(id).get();
        materialRepository.delete(product);
        return product;
    }

    public Material updateMaterial(Integer id, Material material){
        if(materialRepository.findById(id).isPresent()){
            Material auxMaterial =  materialRepository.findById(id).get();
            auxMaterial.setTitle(material.getTitle());
            auxMaterial.setAutor(material.getAutor());
            auxMaterial.setType(material.getType());
            auxMaterial.setDisponible(material.isDisponible());
            auxMaterial.setDescription(material.getDescription());
            auxMaterial.setPublicationYear(material.getPublicationYear());
            return materialRepository.save(auxMaterial);
        }else{
            return null;
        }

    }

    public List<Material> materialByTiltle(String title){
        return materialRepository.findByName(title);
    }

    public Material getMaterialByTitle(String title){
        return materialRepository.getMaterialByName(title);
    }



    public List<MaterialDTO> getFieldsMaterial(){
        return materialRepository.getFieldsMaterials();
    }

}
