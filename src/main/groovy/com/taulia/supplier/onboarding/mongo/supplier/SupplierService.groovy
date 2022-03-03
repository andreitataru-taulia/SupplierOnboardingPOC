package com.taulia.supplier.onboarding.mongo.supplier

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.saasquatch.jsonschemainferrer.*
import groovy.transform.Canonical
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import java.time.DayOfWeek
import java.time.Month

@Service
@Canonical
class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository

    @Autowired
    private ObjectMapper objectMapper

    private static final JsonSchemaInferrer inferrer = JsonSchemaInferrer.newBuilder()
            .setSpecVersion(SpecVersion.DRAFT_06)
    // Requires commons-validator
//            .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip())
            .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
            .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
            .addEnumExtractors(EnumExtractors.validEnum(Month.class),
                    EnumExtractors.validEnum(DayOfWeek.class))
            .build();

    Page<Supplier> getAllSuppliersPaginated(Pageable pageable) {
        String jsonString2 = "{\n" + "  \"type\": \"object\",\n" + "  \"required\": [\n" + "    \"age\"\n" + "  ],\n" + "  \"properties\": {\n" + "    \"firstName\": {\n" + "      \"type\": \"string\",\n" + "      \"minLength\": 2,\n" + "      \"maxLength\": 20\n" + "    },\n" + "    \"lastName\": {\n" + "      \"type\": \"string\",\n" + "      \"minLength\": 5,\n" + "      \"maxLength\": 15\n" + "    },\n" + "    \"age\": {\n" + "      \"type\": \"integer\",\n" + "      \"minimum\": 18,\n" + "      \"maximum\": 100\n" + "    },\n" + "    \"gender\": {\n" + "      \"type\": \"string\",\n" + "      \"enum\": [\n" + "        \"Male\",\n" + "        \"Female\",\n" + "        \"Undisclosed\"\n" + "      ]\n" + "    },\n" + "    \"height\": {\n" + "      \"type\": \"number\"\n" + "    },\n" + "    \"dateOfBirth\": {\n" + "      \"type\": \"string\",\n" + "      \"format\": \"date\"\n" + "    },\n" + "    \"rating\": {\n" + "      \"type\": \"integer\"\n" + "    },\n" + "    \"committer\": {\n" + "      \"type\": \"boolean\"\n" + "    },\n" + "    \"address\": {\n" + "      \"type\": \"object\",\n" + "      \"properties\": {\n" + "        \"street\": {\n" + "          \"type\": \"string\"\n" + "        },\n" + "        \"streetnumber\": {\n" + "          \"type\": \"string\"\n" + "        },\n" + "        \"postalCode\": {\n" + "          \"type\": \"string\"\n" + "        },\n" + "        \"city\": {\n" + "          \"type\": \"string\"\n" + "        }\n" + "      }\n" + "    }\n" + "  }\n" + "}"


        Supplier supplier = objectMapper.readValue(jsonString2, Supplier.class)
        supplier.id = UUID.randomUUID()
        supplierRepository.save(supplier)
        return supplierRepository.findAll(pageable)
    }


    JsonNode getInferredSchema(Object object) {
        return inferrer.inferForSample(objectMapper.valueToTree(object))
    }


}
