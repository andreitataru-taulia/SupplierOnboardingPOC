package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.supplier.Supplier
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface SupplierMapper {

    @Mapping(target = "metaClass", ignore = true)
    SupplierDto to(Supplier supplier)

    @Mapping(target = "metaClass", ignore = true)
    Supplier from(SupplierDto supplierDto)
}
