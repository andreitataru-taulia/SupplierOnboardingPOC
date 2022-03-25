package com.taulia.supplier.onboarding.supplier.model

import lombok.*
import org.hibernate.Hibernate

import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Getter
@Setter
@Builder
@ToString
@Embeddable
@AllArgsConstructor
class Business {
    private String name
    private String tradeNames

    @Enumerated(EnumType.STRING)
    private Type type

    private String priorNameOrLocation
    private String products
    private String countryCode
    private String regionCode
    private Boolean accuracyConfirmed

    // todo move to enum and its own object
    private String taxIdType
    private String taxId


    enum Type {
        CORPORATION,
        LOCAL_BUSINESS
    }

    @Override
    boolean equals(Object o) {
        if (this == o) return true
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false
        Business business = (Business) o
        return name != null && Objects.equals(name, business.name)
                && tradeNames != null && Objects.equals(tradeNames, business.tradeNames)
                && type != null && Objects.equals(type, business.type)
                && priorNameOrLocation != null && Objects.equals(priorNameOrLocation, business.priorNameOrLocation)
                && products != null && Objects.equals(products, business.products)
                && countryCode != null && Objects.equals(countryCode, business.countryCode)
                && regionCode != null && Objects.equals(regionCode, business.regionCode)
                && accuracyConfirmed != null && Objects.equals(accuracyConfirmed, business.accuracyConfirmed)
                && taxIdType != null && Objects.equals(taxIdType, business.taxIdType)
                && taxId != null && Objects.equals(taxId, business.taxId)
    }

    @Override
    int hashCode() {
        return Objects.hash(name,
                tradeNames,
                type,
                priorNameOrLocation,
                products,
                countryCode,
                regionCode,
                accuracyConfirmed,
                taxIdType,
                taxId)
    }
}
