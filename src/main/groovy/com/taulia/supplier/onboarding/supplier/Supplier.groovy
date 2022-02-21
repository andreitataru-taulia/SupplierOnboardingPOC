package com.taulia.supplier.onboarding.supplier

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import lombok.Builder

import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Builder
@ToString
@EqualsAndHashCode
class Supplier {
    @Id
    private UUID id
    private String name
}
