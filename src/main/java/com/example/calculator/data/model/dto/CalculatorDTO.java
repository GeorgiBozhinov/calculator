package com.example.calculator.data.model.dto;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CalculatorDTO {

    @NotEmpty
    private String waxType;

    @NotEmpty
    private String jarOwner;

    private String candleJar;

    @Min(1)
    private Integer waxQuantity;

    @NotEmpty
    private String candleWick;

    @Min(1)
    private Integer wickSize;

    @NotEmpty
    private String scentType;

    @Min(1)
    private Integer scentQuantity;

    private List<String> additionalIngredients;

    public CalculatorDTO() {

    }

    public String getWaxType() {

        return waxType;
    }

    public void setWaxType(String waxType) {

        this.waxType = waxType;
    }

    public String getJarOwner() {

        return jarOwner;
    }

    public void setJarOwner(String jarOwner) {

        this.jarOwner = jarOwner;
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

    public Integer getScentQuantity() {

        return scentQuantity;
    }

    public void setScentQuantity(Integer scentQuantity) {

        this.scentQuantity = scentQuantity;
    }

    public List<String> getAdditionalIngredients() {

        return additionalIngredients;
    }

    public void setAdditionalIngredients(List<String> additionalIngredients) {

        this.additionalIngredients = additionalIngredients;
    }

    @Override
    public String toString() {

        return "CalculatorDTO{" +
                "waxType='" + waxType + '\'' +
                ", jarOwner='" + jarOwner + '\'' +
                ", candleJar='" + candleJar + '\'' +
                ", waxQuantity=" + waxQuantity +
                ", candleWick='" + candleWick + '\'' +
                ", wickSize=" + wickSize +
                ", scentType='" + scentType + '\'' +
                ", scentQuantity=" + scentQuantity +
                '}';
    }

}
