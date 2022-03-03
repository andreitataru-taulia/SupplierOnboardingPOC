package com.taulia.supplier.onboarding.mongo.supplier

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.annotation.Id

@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Supplier {
    @Id
    private UUID id
    private String type
    private Map<String, Object> items
    private Map<String, Object> properties
    private List<Object> required
}
