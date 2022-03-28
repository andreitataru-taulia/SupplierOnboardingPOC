package com.taulia.supplier.onboarding.user

import com.taulia.supplier.onboarding.supplier.model.Supplier
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.*

@Entity
@Builder
@Canonical
@Table(name = "tab_users")
@EntityListeners(AuditingEntityListener.class)
class User {

    @Id
    String email

    String firstName

    String lastName

    @Enumerated(EnumType.STRING)
    Title title

    @Version
    Long version

    @LastModifiedDate
    Date lastUpdated

    @CreatedDate
    Date createdDate

//    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", columnDefinition = "BINARY(16)")
    Supplier supplier

    enum Title {
        FINANCE_DIRECTOR,
        CEO
    }

    @Override
    boolean equals(Object o) {
        if (this == o) return true
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false
        User user = (User) o
        return email != null && Objects.equals(email, user.email)
    }

    @Override
    int hashCode() {
        return getClass().hashCode()
    }
}
