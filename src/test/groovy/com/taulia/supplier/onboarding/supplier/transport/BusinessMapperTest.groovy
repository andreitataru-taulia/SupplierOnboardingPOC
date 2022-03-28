package com.taulia.supplier.onboarding.supplier.transport

import com.taulia.supplier.onboarding.supplier.model.Business
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

import static org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = BusinessMapperImpl.class)
class BusinessMapperTest {

    @Autowired
    private BusinessMapperImpl mapper

    @Test
    void to() {
        //given
        var business =
                Business.builder()
                        .name("Az Incorporated")
                        .type(Business.Type.CORPORATION)
                        .tradeNames("trade name")
                        .taxIdType("TaxId")
                        .taxId("123213213123")
                        .products("az ...")
                        .priorNameOrLocation("az")
                        .countryCode("DE")
                        .regionCode("MUN")
                        .accuracyConfirmed(Boolean.TRUE)
                        .build()

        //when
        var mappedBusinessDto = mapper.to(business)

        //then
        assertEquals(business.getName(), mappedBusinessDto.getName())
        assertEquals(business.getAccuracyConfirmed(), mappedBusinessDto.getAccuracyConfirmed())
        assertEquals(business.getCountryCode(), mappedBusinessDto.getCountryCode())
        assertEquals(business.getRegionCode(), mappedBusinessDto.getRegionCode())
        assertEquals(business.getProducts(), mappedBusinessDto.getProducts())
        assertEquals(business.getPriorNameOrLocation(), mappedBusinessDto.getPriorNameOrLocation())
        assertEquals(business.getTradeNames(), mappedBusinessDto.getTradeNames())
        assertEquals(business.getType(), mappedBusinessDto.getType())
        assertEquals(business.getTaxId(), mappedBusinessDto.getTaxId())
        assertEquals(business.getTaxIdType(), mappedBusinessDto.getTaxIdType())
    }

    @Test
    void from() {
        //given
        var businessDto =
                BusinessDto.builder()
                        .name("Az Incorporated")
                        .type(Business.Type.CORPORATION)
                        .tradeNames("trade name")
                        .taxIdType("TaxId")
                        .taxId("123213213123")
                        .products("az ...")
                        .priorNameOrLocation("az")
                        .countryCode("DE")
                        .regionCode("MUN")
                        .accuracyConfirmed(Boolean.TRUE)
                        .build()

        //when
        var mappedBusiness = mapper.from(businessDto)

        //then
        assertEquals(businessDto.getName(), mappedBusiness.getName())
        assertEquals(businessDto.getAccuracyConfirmed(), mappedBusiness.getAccuracyConfirmed())
        assertEquals(businessDto.getCountryCode(), mappedBusiness.getCountryCode())
        assertEquals(businessDto.getRegionCode(), mappedBusiness.getRegionCode())
        assertEquals(businessDto.getProducts(), mappedBusiness.getProducts())
        assertEquals(businessDto.getPriorNameOrLocation(), mappedBusiness.getPriorNameOrLocation())
        assertEquals(businessDto.getTradeNames(), mappedBusiness.getTradeNames())
        assertEquals(businessDto.getType(), mappedBusiness.getType())
        assertEquals(businessDto.getTaxId(), mappedBusiness.getTaxId())
        assertEquals(businessDto.getTaxIdType(), mappedBusiness.getTaxIdType())
    }
}
