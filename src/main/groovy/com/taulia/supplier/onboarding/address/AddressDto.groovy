package com.taulia.supplier.onboarding.address

import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Builder
@Canonical
class AddressDto {
     UUID id
     String countryCode
     String regionCode
     String city
     String zipCode
     Set<String> addressLines
     Date lastUpdated
     Date createdDate
}
