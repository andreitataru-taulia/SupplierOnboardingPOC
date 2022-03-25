package com.taulia.supplier.onboarding.address

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface AddressMapper {
    AddressDto to(Address address)

//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "supplier", ignore = true)
    Address from(AddressDto addressDto)
}
