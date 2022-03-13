package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.supplier.transport.SupplierDto
import com.taulia.supplier.onboarding.supplier.transport.SupplierMapper
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping("api/v0/suppliers")
class SupplierController {

    @Autowired
    private SupplierService supplierService

    @Autowired
    private SupplierMapper supplierMapper


    SupplierDto createSupplier(SupplierDto supplierDto) {
        return supplierDto.with { supplierMapper::from }
                .with { supplierService::save }
                .with { supplierMapper::to } as SupplierDto
    }


    @Operation(summary = "Retrieve a list with all of the existing Suppliers")
//    @ApiResponse(responseCode = "200", description = "The list of all existing Suppliers",
//            content = @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = Supplier.class)))

    @GetMapping
    List<SupplierDto> getAllSuppliers() {
        return supplierService.getAllSuppliers()
                .stream()
                .map(supplierMapper::to)
                .toList()
    }
}
