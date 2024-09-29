package com.telus.serviceactivation.activation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferParameterDto {
    private String parameterCd;
    private String parameterName;
    private String parameterEffectiveDate;
    private String parameterExpiryDate;
    private String parameterValueTx;
}
