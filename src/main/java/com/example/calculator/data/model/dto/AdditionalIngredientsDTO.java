package com.example.calculator.data.model.dto;

public class AdditionalIngredientsDTO {

   private String additionalIngredientName;
   private Integer count;

    public AdditionalIngredientsDTO() {

    }

    public String getAdditionalIngredientName() {

        return additionalIngredientName;
    }

    public void setAdditionalIngredientName(String additionalIngredientName) {

        this.additionalIngredientName = additionalIngredientName;
    }

    public Integer getCount() {

        return count;
    }

    public void setCount(Integer count) {

        this.count = count;
    }

}
