package com.example.calculator.data.repository;
import com.example.calculator.data.base_entities.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    @Query(value = "SELECT ing.ingredient_name FROM ingredients ing WHERE ing.ingredient_type= ?1", nativeQuery = true)
    List findAllByIngredientType(String ingredientType);

    @Query(value = "SELECT ing.price FROM ingredients ing WHERE ing.ingredient_name = ?1 AND ing.ingredient_type = ?2", nativeQuery = true)
    Double findByIngredientNameAndIngredientType(String ingredientName, String ingredientType);

    List findAllByOrderByIngredientType();

    IngredientEntity findByIngredientName(String ingredientName);

    @Query(value = "SELECT * FROM ingredients ing WHERE ing.id = ?1 ", nativeQuery = true)
    IngredientEntity findByIdCustom(Long id);

}
