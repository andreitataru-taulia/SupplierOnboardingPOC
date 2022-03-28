package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.address.AddressDto
import com.taulia.supplier.onboarding.common.constraint.NotNullMapValue
import com.taulia.supplier.onboarding.user.User
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lombok.EqualsAndHashCode
import org.springframework.hateoas.RepresentationModel

import javax.validation.Valid

@Canonical
@Builder
@EqualsAndHashCode(callSuper = true)
class SupplierDto extends RepresentationModel<SupplierDto> {
    UUID id
    @Valid
    BusinessDto business
    Set<User> authorisedSigners
    Date lastUpdated
    Date createdDate
    Set<AddressDto> addresses
    @NotNullMapValue
    Map<String, Object> extraAttributes
}
