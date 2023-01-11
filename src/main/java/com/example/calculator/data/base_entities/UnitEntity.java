package com.example.calculator.data.base_entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "units")
public class UnitEntity extends BaseEntity{

    @Column(name = "unit_name")
    @NotNull
    private String unitName;

    public UnitEntity() {
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

}
