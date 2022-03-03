package com.taulia.supplier.onboarding

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableSpringDataWebSupport
@SpringBootApplication
class SupplierOnboardingApplication {

    static void main(String[] args) {
        SpringApplication.run(SupplierOnboardingApplication, args)
    }

}
