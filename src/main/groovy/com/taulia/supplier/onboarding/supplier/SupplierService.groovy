package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.supplier.model.Supplier
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
@RequiredArgsConstructor
class SupplierService {
    private final SupplierRepository supplierRepository

    Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier)
    }

    Optional<Supplier> update(UUID userId, Supplier supplier) {
        return supplierRepository
                .findById(userId)
                .map(existingSupplier -> merge(existingSupplier, supplier))
                .map(supplierRepository::save)
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
