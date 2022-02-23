package com.taulia.supplier.onboarding.common.unleashed

import groovy.util.logging.Slf4j
import no.finn.unleash.DefaultUnleash
import no.finn.unleash.Unleash
import no.finn.unleash.util.UnleashConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Slf4j
@Configuration
class UnleashedConfiguration {

    @Bean
    UnleashConfig unleashConfig(@Value("\${unleash.appName}") String appName,
                                @Value("\${unleash.instanceId}") String instanceId,
                                @Value("\${unleash.apiUrl}") String apiUrl,
                                @Value("\${unleash.clientSecret}") String clientSecret) {
        return UnleashConfig.builder()
                .appName(appName)
                .instanceId(instanceId)
                .unleashAPI(apiUrl)
                .customHttpHeader("Authorization", clientSecret)
                .build()
    }

    @Bean
    Unleash unleash(UnleashConfig unleashConfig) {
        var unleash = new DefaultUnleash(unleashConfig)
        unleash.more()
                .evaluateAllToggles()
                .each { log.debug("UNLEASHED found toggle {} variant {} with status {}", it.getName(), it.getVariant(), it.isEnabled()) }
        return unleash
    }
}
