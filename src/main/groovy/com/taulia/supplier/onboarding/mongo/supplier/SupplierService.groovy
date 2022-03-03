package com.taulia.supplier.onboarding.mongo.supplier

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class SupplierService {
    private SupplierRepository supplierRepository
    private ObjectMapper objectMapper

    Page<Supplier> getAllSuppliersPaginated(Pageable pageable) {
        String jsonString2 = "{\n" +
                "    \"type\": \"array\",\n" +
                "    \"items\": {\n" +
                "        \"type\": \"object\",\n" +
                "        \"properties\": {\n" +
                "            \"abc\": {\n" +
                "                \"type\": \"boolean\"\n" +
                "            },\n" +
                "            \"xyz\": {\n" +
                "                \"$ref\": \"#/definitions/\"\n" +
                "            },\n" +
                "            \"asd\": {\n" +
                "                \"type\": \"null\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"required\": [\n" +
                "            \"abc\",\n" +
                "            \"xyz\"\n" +
                "        ]\n" +
                "    }\n" +
                "}";


        Supplier supplier = objectMapper.readValue(jsonString2, Supplier.class);
        supplierRepository.save(supplier)
        return supplierRepository.findAll(pageable)
    }
}
