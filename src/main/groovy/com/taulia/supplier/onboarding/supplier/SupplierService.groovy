package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.supplier.model.Supplier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SupplierService {
    private final SupplierRepository supplierRepository

    @Autowired
    SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository
    }

    Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier)
    }

    Optional<Supplier> update(UUID userId, Supplier supplier) {
        return supplierRepository
                .findById(userId)
                .map(existingSupplier -> merge(existingSupplier, supplier))
                .map(supplierRepository::save) as Optional<Supplier>
    }

    Optional<Supplier> findById(UUID id) {
        return supplierRepository.findById(id)
    }

    Page<Supplier> getAllSuppliersPaginated(Pageable pageable) {
        return supplierRepository.findAll(pageable)
    }

    void delete(UUID id) {
        supplierRepository.findById(id).ifPresent(supplierRepository::delete)
    }

    private Supplier merge(Supplier existingSupplier, Supplier supplier) {
        existingSupplier.setBusiness(supplier.getBusiness())
        existingSupplier.setAddresses(supplier.getAddresses())
        existingSupplier.setAuthorisedSigners(supplier.getAuthorisedSigners())
        existingSupplier.setExtraAttributes(supplier.getExtraAttributes())
        return existingSupplier
    }
}
