package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.supplier.model.Supplier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import java.sql.Date
import java.time.Instant

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    @Test
    void save() {
        // given
        var supplier =
                Supplier.builder()
                        .id(UUID.randomUUID())
                        .business(
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
                                        .build())
                        .addresses(
                                Set.of(
                                        Address.builder()
                                                .id(UUID.randomUUID())
                                                .addressLines(Set.of("address 1", "address 2"))
                                                .countryCode("DE")
                                                .regionCode("MUN")
                                                .city("Munich")
                                                .zipCode("100170")
                                                .createdDate(Date.from(Instant.now()))
                                                .lastUpdated(Date.from(Instant.now()))
                                                .version(0L)
                                                .build()))
                        .authorisedSigners(Set.of(User.builder().email("andrei.tataru@taulia.com").build()))
                        .createdDate(Date.from(Instant.now()))
                        .lastUpdated(Date.from(Instant.now()))
                        .version(0L)
                        .build();
        var supplierArgumentCaptor = ArgumentCaptor.forClass(Supplier.class);

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        // when
        var expectedSupplier = supplierService.save(supplier);

        // then
        assertNotNull(expectedSupplier);
        assertEquals(expectedSupplier, supplier);

        verify(supplierRepository, times(1)).save(supplierArgumentCaptor.capture());
        assertEquals(supplier, supplierArgumentCaptor.getValue());
    }

    @Test
    //todo need to check this again
    void update() {
        // given
        var supplierId = UUID.randomUUID();

        var oldSupplier =
                Supplier.builder()
                        .id(supplierId)
                        .business(
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
                                        .build())
                        .addresses(
                                Set.of(
                                        Address.builder()
                                                .id(UUID.randomUUID())
                                                .addressLines(Set.of("address 1", "address 2"))
                                                .countryCode("DE")
                                                .regionCode("MUN")
                                                .city("Munich")
                                                .zipCode("100170")
                                                .createdDate(Date.from(Instant.now()))
                                                .lastUpdated(Date.from(Instant.now()))
                                                .version(0L)
                                                .build()))
                        .authorisedSigners(Set.of(User.builder().email("andrei.tataru@taulia.com").build()))
                        .createdDate(Date.from(Instant.now()))
                        .lastUpdated(Date.from(Instant.now()))
                        .version(0L)
                        .build();

        var newSupplier =
                Supplier.builder()
                        .id(UUID.randomUUID())
                        .business(
                                Business.builder()
                                        .name("Az Incorporated UPD")
                                        .type(Business.Type.LOCAL_BUSINESS)
                                        .tradeNames("trade name UPD")
                                        .taxIdType("TaxId UPD")
                                        .taxId("123213213123 UPD")
                                        .products("az ... UPD")
                                        .priorNameOrLocation("az UPD")
                                        .countryCode("DE UPD")
                                        .regionCode("MUN UPD")
                                        .accuracyConfirmed(Boolean.FALSE)
                                        .build())
                        .addresses(
                                Set.of(
                                        Address.builder()
                                                .id(UUID.randomUUID())
                                                .addressLines(Set.of("address 1 UPD", "address 2 UPD"))
                                                .countryCode("DE UPD")
                                                .regionCode("MUN UPD")
                                                .city("Munich UPD")
                                                .zipCode("100170 UPD")
                                                .createdDate(Date.from(Instant.now()))
                                                .lastUpdated(Date.from(Instant.now()))
                                                .version(0L)
                                                .build()))
                        .authorisedSigners(Set.of(User.builder().email("andrei.tataru@taulia.com UPD").build()))
                        .createdDate(Date.from(Instant.now()))
                        .lastUpdated(Date.from(Instant.now()))
                        .version(1L)
                        .build();

        var supplierArgumentCaptor = ArgumentCaptor.forClass(Supplier.class);
        var uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        when(supplierRepository.findById(any(UUID.class))).thenReturn(Optional.of(oldSupplier));
        when(supplierRepository.save(any(Supplier.class))).thenReturn(newSupplier);


        // when
        var updatedSupplier = supplierService.update(supplierId, newSupplier);

        // then
        assertNotNull(updatedSupplier);

        verify(supplierRepository, times(1)).findById(uuidArgumentCaptor.capture());
        verify(supplierRepository, times(1)).save(supplierArgumentCaptor.capture());

        assertEquals(supplierArgumentCaptor.getValue().getId(), supplierId);
        assertEquals(supplierArgumentCaptor.getValue().getAddresses(), newSupplier.getAddresses());
        assertEquals(supplierArgumentCaptor.getValue().getAuthorisedSigners(), newSupplier.getAuthorisedSigners());
        assertEquals(supplierArgumentCaptor.getValue().getBusiness(), newSupplier.getBusiness());
        assertEquals(supplierArgumentCaptor.getValue().getExtraAttributes(), newSupplier.getExtraAttributes());
        assertEquals(supplierArgumentCaptor.getValue().getAddresses(), oldSupplier.getAddresses());
    }

    @Test
    void findById() {
        // given
        var id = UUID.randomUUID();
        var supplier =
                Supplier.builder()
                        .id(id)
                        .business(
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
                                        .build())
                        .addresses(
                                Set.of(
                                        Address.builder()
                                                .id(UUID.randomUUID())
                                                .addressLines(Set.of("address 1", "address 2"))
                                                .countryCode("DE")
                                                .regionCode("MUN")
                                                .city("Munich")
                                                .zipCode("100170")
                                                .createdDate(Date.from(Instant.now()))
                                                .lastUpdated(Date.from(Instant.now()))
                                                .version(0L)
                                                .build()))
                        .authorisedSigners(Set.of(User.builder().email("andrei.tataru@taulia.com").build()))
                        .createdDate(Date.from(Instant.now()))
                        .lastUpdated(Date.from(Instant.now()))
                        .version(0L)
                        .build();
        var uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        when(supplierRepository.findById(any(UUID.class))).thenReturn(Optional.of(supplier));

        // when
        var expectedSupplier = supplierService.findById(id);

        // then
        assertNotNull(expectedSupplier);
        assertEquals(expectedSupplier.stream().findFirst().orElseThrow(), supplier);

        verify(supplierRepository, times(1)).findById(uuidArgumentCaptor.capture());
        assertEquals(id, uuidArgumentCaptor.getValue());
    }

    @Test
    void getAllSuppliersPaginated() {
        // given
        var supplier =
                Supplier.builder()
                        .id(UUID.randomUUID())
                        .business(
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
                                        .build())
                        .addresses(
                                Set.of(
                                        Address.builder()
                                                .id(UUID.randomUUID())
                                                .addressLines(Set.of("address 1", "address 2"))
                                                .countryCode("DE")
                                                .regionCode("MUN")
                                                .city("Munich")
                                                .zipCode("100170")
                                                .createdDate(Date.from(Instant.now()))
                                                .lastUpdated(Date.from(Instant.now()))
                                                .version(0L)
                                                .build()))
                        .authorisedSigners(Set.of(User.builder().email("andrei.tataru@taulia.com").build()))
                        .createdDate(Date.from(Instant.now()))
                        .lastUpdated(Date.from(Instant.now()))
                        .version(0L)
                        .build();
        var pagedRequest = PageRequest.of(0, 10);
        Page<Supplier> pagedResponse = new PageImpl<>(List.of(supplier));
        var pageArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(supplierRepository.findAll(any(PageRequest.class))).thenReturn(pagedResponse);

        // when
        var expectedSuppliersPaginated = supplierService.getAllSuppliersPaginated(pagedRequest);

        // then
        assertNotNull(expectedSuppliersPaginated);
        assertEquals(1L, expectedSuppliersPaginated.getTotalPages());
        assertEquals(1L, expectedSuppliersPaginated.getTotalElements());
        assertEquals(List.of(supplier), expectedSuppliersPaginated.getContent());
        assertEquals(
                supplier, expectedSuppliersPaginated.getContent().stream().findFirst().orElseThrow());

        verify(supplierRepository, times(1)).findAll(pageArgumentCaptor.capture());
        assertEquals(pagedRequest, pageArgumentCaptor.getValue());
    }
}
