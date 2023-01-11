package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.UnitEntity;
import com.example.calculator.data.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;

    }

    public List<UnitEntity> getAllUnits(){
        return unitRepository.findAll();
    }

}
