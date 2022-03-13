package com.taulia.supplier.onboarding.supplier

import groovy.transform.TupleConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
@TupleConstructor(includeFields = true, includeProperties = false, force = true)
class SupplierService {
    private final SupplierRepository supplierRepository

    Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier)
    }

    List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll()
    }
}
