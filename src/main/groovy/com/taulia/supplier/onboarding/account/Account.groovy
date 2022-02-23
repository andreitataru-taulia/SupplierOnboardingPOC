package com.taulia.supplier.onboarding.account

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import lombok.Builder

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Builder
@ToString
@EqualsAndHashCode
@Table(name = "tab_account")
class Account {
    @Id
    private UUID id
    private String name
    private String number
    private String ibanNumber
    private String sortCode
    private String swiftCode

    @Version
    private long version
}
