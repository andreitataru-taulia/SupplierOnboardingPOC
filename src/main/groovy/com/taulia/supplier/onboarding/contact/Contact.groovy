package com.taulia.supplier.onboarding.contact

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
@Table(name = "tab_contact")
class Contact {
    @Id
    private UUID id
    private String email
    private String phoneNumber
    private String faxNUmber
    private String contactMethod //TODO make an enum here

    @Version
    private long version
}
