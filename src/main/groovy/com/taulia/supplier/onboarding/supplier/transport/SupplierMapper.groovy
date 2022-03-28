package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.address.AddressDto
import com.taulia.supplier.onboarding.supplier.model.Supplier
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = [BusinessMapper.class, AddressDto.class])
interface SupplierMapper {
    @Mapping(target = "metaClass", ignore = true)
    SupplierDto to(Supplier supplier)

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "addressDto.supplier", ignore = true)
    @Mapping(target = "metaClass", ignore = true)
    Supplier from(SupplierDto supplierDto)
}
