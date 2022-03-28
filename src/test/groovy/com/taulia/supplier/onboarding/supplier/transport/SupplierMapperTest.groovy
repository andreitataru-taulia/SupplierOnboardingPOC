//package com.taulia.supplier.onboarding.supplier.transport
//
//import com.taulia.supplier.onboarding.address.AddressDto
//import com.taulia.supplier.onboarding.address.AddressMapper
//import com.taulia.supplier.onboarding.user.User
//import com.taulia.supplier.onboarding.address.Address
//import com.taulia.supplier.onboarding.supplier.model.Business
//import com.taulia.supplier.onboarding.supplier.model.Supplier
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.InjectMocks
//import org.mockito.junit.jupiter.MockitoExtension
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.test.util.ReflectionTestUtils
//
//import java.sql.Date
//import java.time.Instant
//import java.util.stream.Collectors
//
//import static org.junit.jupiter.api.Assertions.assertEquals
//
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = [BusinessMapper.class, AddressMapper.class])
//class SupplierMapperTest {
//
//    @Autowired private BusinessMapperImpl businessMapper
//    @Autowired private AddressMapperImpl addressMapper
//
//    @InjectMocks private SupplierMapperImpl mapper
//
//    @BeforeEach
//    void init() {
//        ReflectionTestUtils.setField(mapper, "businessMapper", businessMapper)
////    ReflectionTestUtils.setField(mapper, "addressMapper", addressMapper)
//    }
//
//    @Test
//    void to() {
//        // given
//        var supplier =
//                Supplier.builder()
//                        .id(UUID.randomUUID())
//                        .business(
//                                Business.builder()
//                                        .name("Az Incorporated")
//                                        .type(Business.Type.CORPORATION)
//                                        .tradeNames("trade name")
//                                        .taxIdType("TaxId")
//                                        .taxId("123213213123")
//                                        .products("az ...")
//                                        .priorNameOrLocation("az")
//                                        .countryCode("DE")
//                                        .regionCode("MUN")
//                                        .accuracyConfirmed(Boolean.TRUE)
//                                        .build())
//                        .addresses(
//                                Set.of(
//                                        Address.builder()
//                                                .id(UUID.randomUUID())
//                                                .addressLines(Set.of("address 1", "address 2"))
//                                                .countryCode("DE")
//                                                .regionCode("MUN")
//                                                .city("Munich")
//                                                .zipCode("100170")
//                                                .createdDate(Date.from(Instant.now()))
//                                                .lastUpdated(Date.from(Instant.now()))
//                                                .version(0L)
//                                                .build()))
//                        .authorisedSigners(Set.of(User.builder().email("andrei.tataru@taulia.com").build()))
//                        .createdDate(Date.from(Instant.now()))
//                        .lastUpdated(Date.from(Instant.now()))
//                        .version(0L)
//                        .build()
//
//        // when
//        var mappedSupplierDto = mapper.to(supplier)
//
//        // then
//        assertEquals(supplier.getId(), mappedSupplierDto.getId())
//        assertEquals(businessMapper.to(supplier.getBusiness()), mappedSupplierDto.getBusiness())
//        assertEquals(
//                supplier.getAddresses().stream().map(addressMapper::to).collect(Collectors.toSet()),
//                mappedSupplierDto.getAddresses())
//        assertEquals(supplier.getAuthorisedSigners(), mappedSupplierDto.getAuthorisedSigners())
//        assertEquals(supplier.getCreatedDate(), mappedSupplierDto.getCreatedDate())
//        assertEquals(supplier.getLastUpdated(), mappedSupplierDto.getLastUpdated())
//    }
//
//    @Test
//    void from() {
//        var supplierDto =
//                SupplierDto.builder()
//                        .id(UUID.randomUUID())
//                        .business(
//                                BusinessDto.builder()
//                                        .name("Az Incorporated")
//                                        .type(Business.Type.CORPORATION)
//                                        .tradeNames("trade name")
//                                        .taxIdType("TaxId")
//                                        .taxId("123213213123")
//                                        .products("az ...")
//                                        .priorNameOrLocation("az")
//                                        .countryCode("DE")
//                                        .regionCode("MUN")
//                                        .accuracyConfirmed(Boolean.TRUE)
//                                        .build())
//                        .addresses(
//                                Set.of(
//                                        AddressDto.builder()
//                                                .id(UUID.randomUUID())
//                                                .addressLines(Set.of("address 1", "address 2"))
//                                                .countryCode("DE")
//                                                .regionCode("MUN")
//                                                .city("Munich")
//                                                .zipCode("100170")
//                                                .createdDate(Date.from(Instant.now()))
//                                                .lastUpdated(Date.from(Instant.now()))
//                                                .build()))
//                        .authorisedSigners(Set.of(User.builder().email("andrei.tataru@taulia.com").build()))
//                        .createdDate(Date.from(Instant.now()))
//                        .lastUpdated(Date.from(Instant.now()))
//                        .build()
//
//        // when
//        var mappedSupplier = mapper.from(supplierDto)
//
//        // then
//        assertEquals(supplierDto.getId(), mappedSupplier.getId())
//        assertEquals(businessMapper.from(supplierDto.getBusiness()), mappedSupplier.getBusiness())
//        assertEquals(
//                (supplierDto.getAddresses().stream().map(addressMapper::from).collect(Collectors.toSet())),
//                mappedSupplier.getAddresses())
//        assertEquals(supplierDto.getAuthorisedSigners(), mappedSupplier.getAuthorisedSigners())
//        assertEquals(supplierDto.getCreatedDate(), mappedSupplier.getCreatedDate())
//        assertEquals(supplierDto.getLastUpdated(), mappedSupplier.getLastUpdated())
//    }
//}
