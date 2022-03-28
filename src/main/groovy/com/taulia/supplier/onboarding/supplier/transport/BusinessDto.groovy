package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.supplier.model.Business
import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Canonical
@Builder
class BusinessDto {
     String name
     String tradeNames
     Business.Type type
     String priorNameOrLocation
     String products
     String countryCode
     String regionCode
     Boolean accuracyConfirmed
     String taxIdType
     String taxId
}
