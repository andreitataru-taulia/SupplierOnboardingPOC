package com.taulia.supplier.onboarding.supplier


import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version
@Entity
@Table(name = "tab_supplier")
class Supplier {
    @Id
    @GeneratedValue
    UUID id
    String name
    @Column(name= "custom_attributes")
    String customAttributes
    @Version
    long version

}
