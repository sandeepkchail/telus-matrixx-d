package com.telus.serviceactivation.activation.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.telus.serviceactivation.activation.deserializer.RelatedPartyDeserializer;
import com.telus.serviceactivation.activation.entity.ServiceActivation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(using = RelatedPartyDeserializer.class)
public class RelatedPartyRequestDto {
    private Long id;
    private String role;
    private ServiceActivation serviceActivation;
    private String referredType;

}
