package com.telus.serviceactivation.activation.service;

import com.telus.serviceactivation.activation.entity.RelatedParty;
import com.telus.serviceactivation.activation.repository.RelatedPartyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatedPartyService {

    @Autowired
    private RelatedPartyRepository relatedPartyRepository;

    @Transactional
    public RelatedParty getRelatedPartyById(Long id) {
        return relatedPartyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RelatedParty with id " + id + " not found"));
    }
}
