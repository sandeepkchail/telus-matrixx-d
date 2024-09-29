package com.telus.serviceactivation.activation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDto {
    private String offerId;
    private String effectiveDate;
    private String expiryDate;
    private String offerCd;
    private String offerSequenceNumber;
    private String offerTypeCd;
    private String name;
    private String valueType;
    private List<OfferParameterDto> offerParameterList;
}