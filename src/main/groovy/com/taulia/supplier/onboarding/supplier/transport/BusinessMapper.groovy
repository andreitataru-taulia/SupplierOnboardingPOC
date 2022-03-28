package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.supplier.model.Business
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface BusinessMapper {
    @Mapping(target = "metaClass", ignore = true)
    BusinessDto to(Business business);

    @Mapping(target = "metaClass", ignore = true)
    Business from(BusinessDto businessDto);
}
