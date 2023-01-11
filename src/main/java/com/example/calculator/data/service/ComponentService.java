package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.ComponentEntity;
import com.example.calculator.data.repository.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public List<ComponentEntity> getAllComponents(){
        return componentRepository.findAll();
    }


}
