package com.taulia.supplier.onboarding.supplier

import org.everit.json.schema.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

import javax.validation.constraints.NotNull

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus


@Validated
@RestController
@RequestMapping("api/v0/suppliers")
class SupplierController {
    @Autowired
    SupplierService supplierService
    @Autowired
    SupplierMapper supplierMapper

    @PostMapping
    ResponseEntity<SupplierDto> create(@RequestBody @NotNull SupplierDto supplierDto) {
        try {
            return Optional.ofNullable(supplierDto)
                    .map(supplierMapper::from)
                    .map(supplierService::save)
                    .map(supplierMapper::to)
                    .map(persistedSupplier -> new ResponseEntity<>(persistedSupplier, HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)) as ResponseEntity<SupplierDto>
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY)
        }

    }

}
