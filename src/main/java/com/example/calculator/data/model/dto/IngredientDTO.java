package com.example.calculator.data.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class IngredientDTO {

    private Long id;

    @NotEmpty
    private String ingredientType;

    @NotEmpty
    private String ingredientName;

    @Min(1)
    private Integer quantity;

    @Min(1)
    private Integer size;

    @NotEmpty
    private String unitName;

    //@DecimalMin(value = "1.0", inclusive = false)
    private Double price;

    public IngredientDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    @Override
    public String toString() {

        return "IngredientDTO{" +
                "id=" + id +
                ", ingredientType='" + ingredientType + '\'' +
                ", ingredientName='" + ingredientName + '\'' +
                ", quantity=" + quantity +
                ", size=" + size +
                ", unitName='" + unitName + '\'' +
                ", price=" + price +
                '}';
    }

}
