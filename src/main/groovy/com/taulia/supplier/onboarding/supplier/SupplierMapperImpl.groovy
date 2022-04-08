package com.taulia.supplier.onboarding.supplier

class SupplierMapperImpl implements SupplierMapper {


    @Override
    SupplierDto to(Supplier supplier) {
        SupplierDto supplierDto = new SupplierDto()
        supplierDto.setName(supplier.getName())
        supplierDto.setCustomAttributes(supplier.getCustomAttributes())
        return supplierDto
    }

    @Override
    Supplier from(SupplierDto supplierDto) {
        Supplier supplier = new Supplier()
        supplier.setName(supplierDto.getName())
        supplier.setCustomAttributes(supplierDto.getCustomAttributes())
        return supplier
    }
}
