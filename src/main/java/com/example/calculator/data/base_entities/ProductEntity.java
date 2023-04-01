package com.example.calculator.data.base_entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(name = "candle_name")
    @NotEmpty
    private String candleName;

    @Column(name = "wax_type")
    @NotEmpty
    private String waxType;

    @Column(name = "candle_jar")
    @NotEmpty
    private String candleJar;

    @Column(name = "wax_quantity")
    @Min(1)
    private int waxQuantity;

    @Column(name = "candle_wick")
    @NotEmpty
    private String candleWick;

    @Column(name = "wick_size")
    @Min(1)
    private int wickSize;

    @Column(name = "scent_type")
    private String scentType;

    @Column(name = "scent_quantity")
    private int scentQuantity;

    @Column(name = "price")
    private double price;

    @Column(name = "image_name")
    @NotEmpty
    private String imageName;

    public ProductEntity() {
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

    public int getWaxQuantity() {

        return waxQuantity;
    }

    public void setWaxQuantity(int waxQuantity) {

        this.waxQuantity = waxQuantity;
    }

    public String getCandleWick() {

        return candleWick;
    }

    public void setCandleWick(String candleWick) {

        this.candleWick = candleWick;
    }

    public int getWickSize() {

        return wickSize;
    }

    public void setWickSize(int wickSize) {

        this.wickSize = wickSize;
    }

    public String getScentType() {

        return scentType;
    }

    public void setScentType(String scentType) {

        this.scentType = scentType;
    }

    public int getScentQuantity() {

        return scentQuantity;
    }

    public void setScentQuantity(int scentQuantity) {

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

    @Override
    public String toString() {

        return "ProductEntity{" +
                "candleName='" + candleName + '\'' +
                ", waxType='" + waxType + '\'' +
                ", candleJar='" + candleJar + '\'' +
                ", waxQuantity=" + waxQuantity +
                ", candleWick='" + candleWick + '\'' +
                ", wickSize=" + wickSize +
                ", scentType='" + scentType + '\'' +
                ", scentQuantity=" + scentQuantity +
                ", price=" + price +
                ", imageName='" + imageName + '\'' +
                '}';
    }

}
