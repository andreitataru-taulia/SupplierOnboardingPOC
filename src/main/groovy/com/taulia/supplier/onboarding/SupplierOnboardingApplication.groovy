package com.taulia.supplier.onboarding

import com.taulia.supplier.onboarding.supplier.Supplier
import com.taulia.supplier.onboarding.supplier.SupplierDto
import com.taulia.supplier.onboarding.supplier.SupplierMapper
import com.taulia.supplier.onboarding.supplier.SupplierMapperImpl
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableSpringDataWebSupport
@SpringBootApplication
class SupplierOnboardingApplication {

    static void main(String[] args) {
        SpringApplication.run(SupplierOnboardingApplication, args)
    }

    @Bean
    SupplierMapper supplierMapper() {
        return new SupplierMapperImpl()
    }

}
