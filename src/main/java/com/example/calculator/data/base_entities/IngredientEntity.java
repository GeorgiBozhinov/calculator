package com.example.calculator.data.base_entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ingredients")
public class IngredientEntity extends BaseEntity {

    @Column(name = "ingredient_type")
    @NotNull
    @NotEmpty
    private String ingredientType;

    @Column(name = "ingredient_name")
    @NotNull
    @NotEmpty
    private String ingredientName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "size")
    private Integer size;

    @Column(name = "unit_name")
    @NotNull
    private String unitName;

    @Column(name = "price")
    @NotNull
    private Double price;

    public IngredientEntity() {
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

        return "IngredientEntity{" +
                "ingredientType='" + ingredientType + '\'' +
                ", ingredientName='" + ingredientName + '\'' +
                ", quantity=" + quantity +
                ", size=" + size +
                ", unitName='" + unitName + '\'' +
                ", price=" + price +
                '}';
    }

}
