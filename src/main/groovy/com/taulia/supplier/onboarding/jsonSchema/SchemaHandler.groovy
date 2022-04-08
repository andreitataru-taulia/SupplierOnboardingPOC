package com.taulia.supplier.onboarding.jsonSchema

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SchemaHandler {

    public final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule())

    @PostMapping(value = "/addNewAttribute",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    String addCustomAttribute(@RequestBody CustomAttribute customAttribute) {

        String schema = createSchema()
        def schemaTree = OBJECT_MAPPER.readTree(schema)

        def newProperty = ((ObjectNode) schemaTree.path('properties')).putObject(customAttribute.getName())

        newProperty.put('type', customAttribute.getType())
        newProperty.put('description', customAttribute.getDescription())

        def updatedSchema = OBJECT_MAPPER.writeValueAsString(schemaTree)

        return updatedSchema
    }

    private String createSchema() {
        def schema = '''
        {
          "$schema": "https://json-schema.org/draft/2020-12/schema",
          "$id": "https://example.com/product.schema.json",
          "title": "Supplier",
          "description": "A supplier's dynamic attributes",
          "type": "object",
          "properties": {
            "country": {
              "type": "string",
              "description": "Country of incorporation"
            }
          }
        }
        '''
    }

}
