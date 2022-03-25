package com.taulia.supplier.onboarding.address

import com.taulia.supplier.onboarding.supplier.model.Supplier
import lombok.*
import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.*

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@Table(name = "tab_address")
@EntityListeners(AuditingEntityListener.class)
class Address {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)", nullable = false)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id

    private String countryCode
    private String regionCode
    private String city
    private String zipCode

    @Builder.Default
    @CollectionTable(name = "tab_address_address_lines",
            joinColumns = @JoinColumn(name = "address_id", columnDefinition = "BINARY(16)"))
    @ElementCollection
    private Set<String> addressLines = new HashSet<>()

    @Version
    private Long version

    @LastModifiedDate
    private Date lastUpdated

    @CreatedDate
    private Date createdDate

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", columnDefinition = "BINARY(16)")
    private Supplier supplier

    @Override
    boolean equals(Object o) {
        if (this == o) return true
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false
        Address address = (Address) o
        return id != null && Objects.equals(id, address.id)
    }

    @Override
    int hashCode() {
        return getClass().hashCode()
    }
}
