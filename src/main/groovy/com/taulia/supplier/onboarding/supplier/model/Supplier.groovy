package com.taulia.supplier.onboarding.supplier.model

import com.taulia.supplier.onboarding.address.Address
import com.taulia.supplier.onboarding.common.db.JsonMapUserType
import com.taulia.supplier.onboarding.user.User
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.*

@Entity
@Builder
@Canonical
@Table(name = "tab_suppliers")
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "JsonMapUserType", typeClass = JsonMapUserType.class)
class Supplier {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)", nullable = false)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    UUID id

    @Embedded
    Business business

//   @Builder.Default
//   @ToString.Exclude
    @OneToMany(
            mappedBy = "supplier",
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    Set<User> authorisedSigners = new HashSet<>()

//   @Builder.Default
//   @ToString.Exclude
    @OneToMany(mappedBy = "supplier")
    Set<Address> addresses = new HashSet<>()

//   @Builder.Default
    @Type(type = "JsonMapUserType")
    @Column(name = "extra_attributes", columnDefinition = "json")
    Map<String, Object> extraAttributes = new HashMap<>()

    @Version
    Long version

    @LastModifiedDate
    Date lastUpdated

    @CreatedDate
    Date createdDate

   @Override
   boolean equals(Object o) {
      if (this == o) return true
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false
      Supplier supplier = (Supplier) o
      return id != null && Objects.equals(id, supplier.id)
   }

   @Override
   int hashCode() {
      return getClass().hashCode()
   }
}
