package com.taulia.supplier.onboarding.address

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

import java.sql.Date
import java.time.Instant

import static org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AddressMapperTest {

    @Autowired
    private AddressMapper mapper

    @Test
    void to() {
        // given
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

        // when
        var mappedAddressDto = mapper.to(address)

        // then
        assertEquals(address.getId(), mappedAddressDto.getId())
        assertEquals(address.getAddressLines(), mappedAddressDto.getAddressLines())
        assertEquals(address.getCity(), mappedAddressDto.getCity())
        assertEquals(address.getCountryCode(), mappedAddressDto.getCountryCode())
        assertEquals(address.getRegionCode(), mappedAddressDto.getRegionCode())
        assertEquals(address.getZipCode(), mappedAddressDto.getZipCode())
        assertEquals(address.getCreatedDate(), mappedAddressDto.getCreatedDate())
        assertEquals(address.getLastUpdated(), mappedAddressDto.getLastUpdated())

    }

    @Test
    void from() {
        // given
        var addressDto =
                AddressDto.builder()
                        .id(UUID.randomUUID())
                        .addressLines(Set.of("address 1", "address 2"))
                        .countryCode("DE")
                        .regionCode("MUN")
                        .city("Munich")
                        .zipCode("100170")
                        .createdDate(Date.from(Instant.now()))
                        .lastUpdated(Date.from(Instant.now()))
                        .build()

        // when
        var mappedAddress = mapper.from(addressDto)

        // then
        assertEquals(addressDto.getId(), mappedAddress.getId())
        assertEquals(addressDto.getAddressLines(), mappedAddress.getAddressLines())
        assertEquals(addressDto.getCity(), mappedAddress.getCity())
        assertEquals(addressDto.getCountryCode(), mappedAddress.getCountryCode())
        assertEquals(addressDto.getRegionCode(), mappedAddress.getRegionCode())
        assertEquals(addressDto.getZipCode(), mappedAddress.getZipCode())
        assertEquals(addressDto.getCreatedDate(), mappedAddress.getCreatedDate())
        assertEquals(addressDto.getLastUpdated(), mappedAddress.getLastUpdated())

    }
}
