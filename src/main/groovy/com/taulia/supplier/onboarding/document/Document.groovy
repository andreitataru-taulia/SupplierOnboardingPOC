package com.taulia.supplier.onboarding.document

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.*

@Entity
@Builder
@Canonical
@Table(name = "tab_documents")
@EntityListeners(AuditingEntityListener.class)
class Document {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)", nullable = false)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id

    private String name

    private String fileName

    private Long fileSize

    @Version
    private Long version

    @LastModifiedDate
    private Date lastUpdated

    @CreatedDate
    private Date createdDate

    @Override
    boolean equals(Object o) {
        if (this == o) return true
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false
        Document document = (Document) o
        return id != null && Objects.equals(id, document.id)
    }

    @Override
    int hashCode() {
        return getClass().hashCode()
    }
}
