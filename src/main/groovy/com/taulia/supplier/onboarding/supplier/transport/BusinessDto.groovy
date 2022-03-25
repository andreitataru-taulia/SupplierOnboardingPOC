package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.supplier.model.Business
import lombok.Builder
import lombok.Data

@Data
@Builder
class BusinessDto {
    private String name
    private String tradeNames
    private Business.Type type
    private String priorNameOrLocation
    private String products
    private String countryCode
    private String regionCode
    private Boolean accuracyConfirmed
    private String taxIdType
    private String taxId
}
