package com.taulia.supplier.onboarding.supplier

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
@RequiredArgsConstructor
class SupplierService {
    private SupplierRepository supplierRepository

    Supplier save(Supplier supplier){
        return supplierRepository.save(supplier)
    }

    List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll()
    }
}
