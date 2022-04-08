package com.taulia.supplier.onboarding.supplier

import org.everit.json.schema.Schema
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONTokener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.json.JSONObject
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SupplierService {

    static final String SCHEMA = '''
{
  "$schema": "https://json-schema.org/schema",
  "$id": "https://business.schema.json",
  "title": "Supplier dynamic attributes",
  "description": "A supplier dynamic attributes",
  "type": "object",
  "properties": {
    "country": {
      "description": "country",
      "type": "string"
    },
    "yearOfIncorporation": {
      "description": "year of incorporation",
      "type": "string"
    }
  },
  "additionalProperties": false
 }
'''

    @Autowired
    final SupplierRepository supplierRepository;

    Supplier save(Supplier supplier) {
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(SCHEMA))

        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(supplier.customAttributes))
        Schema schema = SchemaLoader.load(jsonSchema);

        schema.validate(jsonSubject)

        return supplierRepository.save(supplier)

    }
}