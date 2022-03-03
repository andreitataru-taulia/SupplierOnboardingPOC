package com.taulia.supplier.onboarding.mongo.supplier

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController("api/v0/suppliers")
class SupplierController {

    private SupplierService supplierService

    @Operation(summary = "Retrieve a list with all of the existing Suppliers")
    @ApiResponse(responseCode = "200", description = "The list of all existing Suppliers"
//            ,content = @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = Supplier.class)
//            )
    )

    @GetMapping
    Page<Supplier> getSuppliersPaginated(Pageable pageable) {
        return supplierService.getAllSuppliersPaginated(pageable)
    }
}
