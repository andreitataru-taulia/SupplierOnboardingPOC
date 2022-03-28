package com.taulia.supplier.onboarding.address

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface AddressMapper {
    @Mapping(target = "metaClass", ignore = true)
    AddressDto to(Address address)

    @Mapping(target = "metaClass", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    Address from(AddressDto addressDto)
}
