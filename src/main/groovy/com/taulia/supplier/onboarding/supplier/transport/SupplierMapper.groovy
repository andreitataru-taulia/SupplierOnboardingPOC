package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.address.AddressDto
import com.taulia.supplier.onboarding.supplier.model.Supplier
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = [BusinessMapper.class, AddressDto.class])
interface SupplierMapper {
    SupplierDto to(Supplier supplier)

//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "addressDto.supplier", ignore = true)
    Supplier from(SupplierDto supplierDto)
}
