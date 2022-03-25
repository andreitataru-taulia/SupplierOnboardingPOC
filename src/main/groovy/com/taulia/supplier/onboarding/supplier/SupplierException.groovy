package com.taulia.supplier.onboarding.supplier

import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class SupplierException {
    static final String NOT_ON_FREE_PLAN = "Not on free plan"

    static HttpClientErrorException notOnFreePlan() {
        return new HttpClientErrorException(HttpStatus.BAD_REQUEST, NOT_ON_FREE_PLAN)
    }
}
