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
    UUID id
    String type
    Map<String, Object> items
    Map<String, Object> properties
    List<Object> required
}
