package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.MysqlContainerInitializer
import com.taulia.supplier.onboarding.TestProfile
import com.taulia.supplier.onboarding.address.Address
import com.taulia.supplier.onboarding.supplier.model.Business
import com.taulia.supplier.onboarding.supplier.model.Supplier
import com.taulia.supplier.onboarding.user.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest

import java.time.Instant

import static org.junit.jupiter.api.Assertions.*

@TestProfile
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SupplierRepositoryTest implements MysqlContainerInitializer {

    @Autowired
    private SupplierRepository supplierRepository

    @Test
    void testSupplierCanBePersisted() {
        // given
        var businessName = "Az Incorporated"
        var accuracyConfirmed = true
        var countryCode = "DE"
        var priorNameOrLocation = "Somewhere"
        var products = "pr1"
        var regionCode = "MUN"
        var taxId = "1999999"
        var taxIdType = "TaxId"
        var tradeNames = "trade"
        var type = Business.Type.CORPORATION
        var business =
                Business.builder()
                        .name(businessName)
                        .accuracyConfirmed(accuracyConfirmed)
                        .countryCode(countryCode)
                        .priorNameOrLocation(priorNameOrLocation)
                        .products(products)
                        .regionCode(regionCode)
                        .taxId(taxId)
                        .taxIdType(taxIdType)
                        .tradeNames(tradeNames)
                        .type(type)
                        .build()
        var extraObjKey = "extra_business"
        var address =
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
                        .build()
        var user = User.builder().email("andrei.tataru@taulia.com").build()
        var supplier =
                Supplier.builder()
                        .business(business)
                        .addresses(Set.of(address))
                        .authorisedSigners(Set.of(user))
                        .extraAttributes(Map.of(extraObjKey, business))
                        .build()

        // when
        Supplier persistedSupplier = supplierRepository.save(supplier)

        // then
        assertNotNull(persistedSupplier)
        assertNotNull(persistedSupplier.getVersion())
        assertNotNull(persistedSupplier.getCreatedDate())
        assertNotNull(persistedSupplier.getLastUpdated())
        assertNotNull(persistedSupplier.getBusiness())

        assertEquals(businessName, persistedSupplier.getBusiness().getName())
        assertEquals(accuracyConfirmed, persistedSupplier.getBusiness().getAccuracyConfirmed())
        assertEquals(countryCode, persistedSupplier.getBusiness().getCountryCode())
        assertEquals(priorNameOrLocation, persistedSupplier.getBusiness().getPriorNameOrLocation())
        assertEquals(products, persistedSupplier.getBusiness().getProducts())
        assertEquals(regionCode, persistedSupplier.getBusiness().getRegionCode())
        assertEquals(taxId, persistedSupplier.getBusiness().getTaxId())
        assertEquals(taxIdType, persistedSupplier.getBusiness().getTaxIdType())
        assertEquals(tradeNames, persistedSupplier.getBusiness().getTradeNames())
        assertEquals(type, persistedSupplier.getBusiness().getType())

        assertNotNull(persistedSupplier.getAuthorisedSigners())
        assertFalse(persistedSupplier.getAuthorisedSigners().isEmpty())
        assertEquals(user, persistedSupplier.getAuthorisedSigners().stream().findFirst().orElseThrow())

        assertNotNull(persistedSupplier.getAddresses())
        assertFalse(persistedSupplier.getAddresses().isEmpty())
        assertEquals(address, persistedSupplier.getAddresses().stream().findFirst().orElseThrow())

        assertNotNull(persistedSupplier.getExtraAttributes())
        assertFalse(persistedSupplier.getExtraAttributes().isEmpty())
        assertTrue(persistedSupplier.getExtraAttributes().containsKey(extraObjKey))
        assertEquals(business, persistedSupplier.getExtraAttributes().get(extraObjKey))
    }

    @Test
    void testSupplierCanBePersistedWithoutExtraAttribute() {
        // given
        var supplier = Supplier.builder().build()

        // when
        Supplier persistedSupplier = supplierRepository.save(supplier)

        // then
        assertNotNull(persistedSupplier)
        assertNotNull(persistedSupplier.getVersion())
        assertNotNull(persistedSupplier.getCreatedDate())
        assertNotNull(persistedSupplier.getLastUpdated())

        assertNotNull(persistedSupplier.getExtraAttributes())
        assertTrue(persistedSupplier.getExtraAttributes().isEmpty())
    }

    @Test
    void testSupplierCanBePersistedWithNullObjectInExtraAttribute() {
        // given
        var extraObjKey = "extraObjKey"
        var supplier = Supplier.builder().extraAttributes(Map.of(extraObjKey, null)).build()

        // when
        Supplier persistedSupplier = supplierRepository.save(supplier)

        // then
        assertNotNull(persistedSupplier)
        assertNotNull(persistedSupplier.getVersion())
        assertNotNull(persistedSupplier.getCreatedDate())
        assertNotNull(persistedSupplier.getLastUpdated())

        assertNotNull(persistedSupplier.getExtraAttributes())
        assertTrue(persistedSupplier.getExtraAttributes().isEmpty())
        assertTrue(persistedSupplier.getExtraAttributes().containsKey(extraObjKey))
        //    assertEquals(business, persistedSupplier.getExtraAttributes().get(extraObjKey))
    }

    @Test
    void testSupplierCanBeRetrievedPaginated() {
        // given
        var businessName = "Az Incorporated"
        var accuracyConfirmed = true
        var countryCode = "DE"
        var priorNameOrLocation = "Somewhere"
        var products = "pr1"
        var regionCode = "MUN"
        var taxId = "1999999"
        var taxIdType = "TaxId"
        var tradeNames = "trade"
        var type = Business.Type.CORPORATION
        var business =
                Business.builder()
                        .name(businessName)
                        .accuracyConfirmed(accuracyConfirmed)
                        .countryCode(countryCode)
                        .priorNameOrLocation(priorNameOrLocation)
                        .products(products)
                        .regionCode(regionCode)
                        .taxId(taxId)
                        .taxIdType(taxIdType)
                        .tradeNames(tradeNames)
                        .type(type)
                        .build()
        var extraObjKey = "extra_business"
        var address =
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
                        .build()
        var user = User.builder().email("andrei.tataru@taulia.com").build()
        var supplier =
                Supplier.builder()
                        .business(business)
                        .addresses(Set.of(address))
                        .authorisedSigners(Set.of(user))
                        .extraAttributes(Map.of(extraObjKey, business))
                        .build()

        supplierRepository.save(supplier)

        var pagedRequest = PageRequest.of(0, 10)

        // when
        var retrievedPagedSuppliers = supplierRepository.findAll(pagedRequest)

        // then
        assertNotNull(retrievedPagedSuppliers)
        assertEquals(pagedRequest, retrievedPagedSuppliers.getPageable())

        Supplier persistedSupplier =
                retrievedPagedSuppliers.getContent().stream().findFirst().orElseThrow()
        assertNotNull(persistedSupplier)
        assertNotNull(persistedSupplier.getVersion())
        assertNotNull(persistedSupplier.getCreatedDate())
        assertNotNull(persistedSupplier.getLastUpdated())
        assertNotNull(persistedSupplier.getBusiness())

        assertEquals(businessName, persistedSupplier.getBusiness().getName())
        assertEquals(accuracyConfirmed, persistedSupplier.getBusiness().getAccuracyConfirmed())
        assertEquals(countryCode, persistedSupplier.getBusiness().getCountryCode())
        assertEquals(priorNameOrLocation, persistedSupplier.getBusiness().getPriorNameOrLocation())
        assertEquals(products, persistedSupplier.getBusiness().getProducts())
        assertEquals(regionCode, persistedSupplier.getBusiness().getRegionCode())
        assertEquals(taxId, persistedSupplier.getBusiness().getTaxId())
        assertEquals(taxIdType, persistedSupplier.getBusiness().getTaxIdType())
        assertEquals(tradeNames, persistedSupplier.getBusiness().getTradeNames())
        assertEquals(type, persistedSupplier.getBusiness().getType())

        assertNotNull(persistedSupplier.getAuthorisedSigners())
        assertFalse(persistedSupplier.getAuthorisedSigners().isEmpty())
        assertEquals(user, persistedSupplier.getAuthorisedSigners().stream().findFirst().orElseThrow())

        assertNotNull(persistedSupplier.getAddresses())
        assertFalse(persistedSupplier.getAddresses().isEmpty())
        assertEquals(address, persistedSupplier.getAddresses().stream().findFirst().orElseThrow())

        assertNotNull(persistedSupplier.getExtraAttributes())
        assertFalse(persistedSupplier.getExtraAttributes().isEmpty())
        assertTrue(persistedSupplier.getExtraAttributes().containsKey(extraObjKey))
        assertEquals(business, persistedSupplier.getExtraAttributes().get(extraObjKey))
    }

    @Test
    void testSupplierCanBeDeleted() {
        // given
        var businessName = "Az Incorporated"
        var accuracyConfirmed = true
        var countryCode = "DE"
        var priorNameOrLocation = "Somewhere"
        var products = "pr1"
        var regionCode = "MUN"
        var taxId = "1999999"
        var taxIdType = "TaxId"
        var tradeNames = "trade"
        var type = Business.Type.CORPORATION
        var business =
                Business.builder()
                        .name(businessName)
                        .accuracyConfirmed(accuracyConfirmed)
                        .countryCode(countryCode)
                        .priorNameOrLocation(priorNameOrLocation)
                        .products(products)
                        .regionCode(regionCode)
                        .taxId(taxId)
                        .taxIdType(taxIdType)
                        .tradeNames(tradeNames)
                        .type(type)
                        .build()
        var extraObjKey = "extra_business"
        var address =
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
                        .build()
        var user = User.builder().email("andrei.tataru@taulia.com").build()
        var supplier =
                Supplier.builder()
                        .business(business)
                        .addresses(Set.of(address))
                        .authorisedSigners(Set.of(user))
                        .extraAttributes(Map.of(extraObjKey, business))
                        .build()

        Supplier persistedSupplier = supplierRepository.save(supplier)

        // when
        supplierRepository.delete(supplier)

        // then
        assertTrue(supplierRepository.findById(persistedSupplier.getId()).isEmpty())
    }
}
