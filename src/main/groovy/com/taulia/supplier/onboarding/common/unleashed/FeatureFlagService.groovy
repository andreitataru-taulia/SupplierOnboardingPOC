package com.taulia.supplier.onboarding.common.unleashed

import lombok.RequiredArgsConstructor
import no.finn.unleash.Unleash
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class FeatureFlagService {
    private Unleash unleash

    boolean isFeatureEnabled(String featureFlag) {
        return unleash.isEnabled(featureFlag)
    }
}
