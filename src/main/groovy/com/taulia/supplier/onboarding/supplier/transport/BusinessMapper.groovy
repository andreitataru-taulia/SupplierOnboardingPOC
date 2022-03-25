package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.supplier.model.Business
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface BusinessMapper {
    BusinessDto to(Business business);

    Business from(BusinessDto businessDto);
}
