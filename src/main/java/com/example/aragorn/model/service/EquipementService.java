package com.example.aragorn.model.service;

import org.springframework.stereotype.Service;

import com.example.aragorn.model.repository.EquipementRepository;

@Service
public class EquipementService {
    @Autowired private EquipementRepository equipementRepository;

}
