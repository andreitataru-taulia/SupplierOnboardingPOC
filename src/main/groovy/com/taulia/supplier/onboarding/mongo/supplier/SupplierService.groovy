package com.taulia.supplier.onboarding.mongo.supplier

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.Canonical
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@Canonical
class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository

    @Autowired
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
                "                \"\$ref\": \"#/definitions/\"\n" +
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


        Supplier supplier = objectMapper.readValue(jsonString2, Supplier.class)
        supplier.id = UUID.randomUUID()
        supplierRepository.save(supplier)
        return supplierRepository.findAll(pageable)
    }
}
