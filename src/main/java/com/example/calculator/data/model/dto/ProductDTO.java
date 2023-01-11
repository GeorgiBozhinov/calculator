package com.example.calculator.data.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class ProductDTO {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9\\s_.,-]+$|^[а-яА-Я0-9\\s_.,-]+$")
    private String candleName;

    @NotEmpty
    private String waxType;

    @NotEmpty
    private String candleJar;

    @Min(1)
    private Integer waxQuantity;

    @NotEmpty
    private String candleWick;

    @Min(1)
    private Integer wickSize;

    @NotEmpty
    private String scentType;

    private Double scentQuantity;

    public double price;

    private String imageName;

    private List<String> additionalIngredients;

    public ProductDTO() {

    }

    public String getCandleName() {

        return candleName;
    }

    public void setCandleName(String candleName) {

        this.candleName = candleName;
    }

    public String getWaxType() {

        return waxType;
    }

    public void setWaxType(String waxType) {

        this.waxType = waxType;
    }

    public String getCandleJar() {

        return candleJar;
    }

    public void setCandleJar(String candleJar) {

        this.candleJar = candleJar;
    }

    public Integer getWaxQuantity() {

        return waxQuantity;
    }

    public void setWaxQuantity(Integer waxQuantity) {

        this.waxQuantity = waxQuantity;
    }

    public String getCandleWick() {

        return candleWick;
    }

    public void setCandleWick(String candleWick) {

        this.candleWick = candleWick;
    }

    public Integer getWickSize() {

        return wickSize;
    }

    public void setWickSize(Integer wickSize) {

        this.wickSize = wickSize;
    }

    public String getScentType() {

        return scentType;
    }

    public void setScentType(String scentType) {

        this.scentType = scentType;
    }

    public Double getScentQuantity() {

        return scentQuantity;
    }

    public void setScentQuantity(Double scentQuantity) {

        this.scentQuantity = scentQuantity;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public String getImageName() {

        return imageName;
    }

    public void setImageName(String imageName) {

        this.imageName = imageName;
    }

    public List<String> getAdditionalIngredients() {

        return additionalIngredients;
    }

    public void setAdditionalIngredients(List<String> additionalIngredients) {

        this.additionalIngredients = additionalIngredients;
    }

    @Override
    public String toString() {

        return "ProductDTO{" +
                "candleName='" + candleName + '\'' +
                ", waxType='" + waxType + '\'' +
                ", candleJar='" + candleJar + '\'' +
                ", waxQuantity=" + waxQuantity +
                ", candleWick='" + candleWick + '\'' +
                ", wickSize=" + wickSize +
                ", scentType='" + scentType + '\'' +
                ", scentQuantity='" + scentQuantity + '\'' +
                ", price=" + price +
                ", imageName='" + imageName + '\'' +
                '}';
    }

}
