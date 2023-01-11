package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.model.dto.IngredientDTO;
import com.example.calculator.data.repository.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    private final ModelMapper modelMapper;

    public IngredientService(IngredientRepository ingredientRepository, ModelMapper modelMapper) {

        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }

    public void addIngredient(IngredientDTO ingredientDTO) {
        IngredientEntity ingredientEntity = modelMapper.map(ingredientDTO, IngredientEntity.class);
        ingredientRepository.save(ingredientEntity);
    }

    public void updateIngredient(IngredientDTO ingredientDTO, Long id) {
        IngredientEntity ingEntity = findIngredientById(id);
        ingredientDTO.setIngredientType(ingEntity.getIngredientType());

        IngredientEntity ingredientEntity = modelMapper.map(ingredientDTO, IngredientEntity.class);
        ingredientRepository.save(ingredientEntity);
    }

    public void deleteIngredientById(Long ingredientId) {

        ingredientRepository.deleteById(ingredientId);
    }

    public List getAllIngredients() {

        return ingredientRepository.findAllByOrderByIngredientType();
    }

    public List findByIngredientType(String ingredientType){
        return ingredientRepository.findAllByIngredientType(ingredientType);
    }

    public Optional<IngredientEntity> findIngredient(Long ingredientId) {

        return ingredientRepository.findById(ingredientId);
    }

    public IngredientEntity findIngredientById(Long ingredientId) {

        return ingredientRepository.findByIdCustom(ingredientId);
    }

    public IngredientEntity checkIfExistSuchIngredient(String ingredientName){
        return ingredientRepository.findByIngredientName(ingredientName);
    }

}
