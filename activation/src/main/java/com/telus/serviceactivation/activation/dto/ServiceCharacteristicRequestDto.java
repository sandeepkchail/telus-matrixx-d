package com.telus.serviceactivation.activation.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.telus.serviceactivation.activation.deserializer.RelatedPartyDeserializer;
import com.telus.serviceactivation.activation.entity.ServiceActivation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCharacteristicRequestDto {
    private Long id;
    private String name;
    private String valueContent;
    private String valueType;
    private ServiceActivation serviceActivation;

}
