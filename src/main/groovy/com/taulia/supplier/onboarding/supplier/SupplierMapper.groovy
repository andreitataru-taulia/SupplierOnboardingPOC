package com.taulia.supplier.onboarding.supplier

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SupplierMapper {
    SupplierDto to(Supplier supplier)

    Supplier from(SupplierDto supplierDto)
}
