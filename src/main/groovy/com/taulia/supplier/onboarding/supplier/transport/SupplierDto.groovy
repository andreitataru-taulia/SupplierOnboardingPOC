package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.address.AddressDto
import com.taulia.supplier.onboarding.common.constraint.NotNullMapValue
import com.taulia.supplier.onboarding.user.User
import lombok.Builder
import lombok.Data
import lombok.EqualsAndHashCode
import org.springframework.hateoas.RepresentationModel

import javax.validation.Valid

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
class SupplierDto extends RepresentationModel<SupplierDto> {
    private UUID id
    @Valid
    private BusinessDto business
    private Set<User> authorisedSigners
    private Date lastUpdated
    private Date createdDate
    private Set<AddressDto> addresses
    @NotNullMapValue
    private Map<String, Object> extraAttributes
}
