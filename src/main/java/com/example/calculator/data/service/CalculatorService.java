package com.example.calculator.data.service;

import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.model.dto.CalculatorDTO;
import com.example.calculator.data.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CalculatorService {

    private final IngredientRepository ingredientRepository;

    public CalculatorService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    // TODO
    // Validate the returned object, does exist or not. If do not exist, return message, but not error
    public Double calcPrice(CalculatorDTO calculatorDTO){
        String waxType = calculatorDTO.getWaxType();
        String jarOwner = calculatorDTO.getJarOwner();
        String candleJar = calculatorDTO.getCandleJar();
        int waxQuantity = calculatorDTO.getWaxQuantity();
        String wickType = calculatorDTO.getCandleWick();
        int wickSize = calculatorDTO.getWickSize();
        String scentType = calculatorDTO.getScentType();
        int scentQuantity = calculatorDTO.getScentQuantity();

        IngredientEntity ingredientEntity;

        Double finalPrice = 0.0;

        if(!waxType.isEmpty() && waxQuantity > 1){
            ingredientEntity = ingredientRepository.findByIngredientName(waxType);
            Double waxPrice = ingredientEntity.getPrice();
            Integer waxQuantityDB = ingredientEntity.getQuantity();
            String unitType = ingredientEntity.getUnitName();

            if(Objects.equals(unitType, "кг")){
                waxQuantityDB = waxQuantityDB * 1000;
            }

            finalPrice = waxPrice/(waxQuantityDB/waxQuantity);

        }

        if(jarOwner.equals("seller")){
            ingredientEntity = ingredientRepository.findByIngredientName(candleJar);
            finalPrice += ingredientEntity.getPrice();
        }

        if(!wickType.isEmpty()){
            ingredientEntity = ingredientRepository.findByIngredientName(wickType);
            Double wickPrice = ingredientEntity.getPrice();
            Integer wickSizeDB = ingredientEntity.getSize();

            finalPrice += wickPrice/(wickSizeDB/wickSize);
        }

        if(!scentType.isEmpty() && scentQuantity > 1){
            ingredientEntity = ingredientRepository.findByIngredientName(scentType);
            Double scentPrice = ingredientEntity.getPrice();
            Integer scentQuantityDB = ingredientEntity.getQuantity();

            finalPrice += scentPrice/(scentQuantityDB/scentQuantity);
        }

        List<String> additionalIngredients = calculatorDTO.getAdditionalIngredients();

        if(additionalIngredients.size() >= 1){
            for (String ingredient : additionalIngredients) {
                String[] spllitted = ingredient.split("-");

                if(spllitted.length > 1){
                    ingredientEntity = ingredientRepository.findByIngredientName(spllitted[0]);
                    double temp = ingredientEntity.getPrice() * Integer.parseInt(spllitted[1]);
                    //System.out.println(spllitted[0] + ": " + temp);
                    finalPrice += temp;
                }
            }
        }

        finalPrice = Math.round(finalPrice * 100.0) / 100.0;

        return finalPrice;
    }

}
