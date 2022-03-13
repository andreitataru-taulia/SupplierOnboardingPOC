package com.taulia.supplier.onboarding.supplier

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Canonical
@Entity()
@Table(name = "tab_supplier")
class Supplier {

    @Id
    private UUID id

    private String firstName;
}
