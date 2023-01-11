package com.example.calculator.data.base_entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "component_names")
public class ComponentEntity extends BaseEntity {

    @Column(name="component_name")
    @NotEmpty
    public String componentName;

    @Column(name = "component_name_en")
    @NotEmpty
    public String componentNameEn;

    public ComponentEntity() {
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentNameEn() {
        return componentNameEn;
    }

    public void setComponentNameEn(String componentNameEn) {
        this.componentNameEn = componentNameEn;
    }

}
