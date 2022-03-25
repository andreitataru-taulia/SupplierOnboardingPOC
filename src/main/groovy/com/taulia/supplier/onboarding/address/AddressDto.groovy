package com.taulia.supplier.onboarding.address

import lombok.Builder
import lombok.Data

@Data
@Builder
class AddressDto {
    private UUID id
    private String countryCode
    private String regionCode
    private String city
    private String zipCode
    private Set<String> addressLines
    private Date lastUpdated
    private Date createdDate
}
