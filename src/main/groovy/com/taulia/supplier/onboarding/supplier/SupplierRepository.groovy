package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.supplier.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SupplierRepository extends JpaRepository<Supplier, UUID> {}
