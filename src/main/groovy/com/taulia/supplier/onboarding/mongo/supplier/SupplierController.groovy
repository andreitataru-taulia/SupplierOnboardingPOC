//package com.taulia.supplier.onboarding.mongo.supplier
//
//import com.fasterxml.jackson.databind.JsonNode
//import groovy.transform.Canonical
//import io.swagger.v3.oas.annotations.Operation
//import io.swagger.v3.oas.annotations.responses.ApiResponse
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.domain.Page
//import org.springframework.data.domain.Pageable
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RestController
//
//@Canonical
//@RestController("api/v0/suppliers")
////TODO why this no longer works...
//class SupplierController {
//
//    @Autowired
//    private SupplierService supplierService
//
//    @Operation(summary = "Retrieve a list with all of the existing Suppliers")
//    @ApiResponse(responseCode = "200", description = "The list of all existing Suppliers"
////            ,content = @Content(mediaType = "application/json",
////                    schema = @Schema(implementation = Supplier.class)
////            )
//    )
//
//    @GetMapping("api/v0/suppliers")
//    Page<Supplier> getSuppliersPaginated(Pageable pageable) {
//        return supplierService.getAllSuppliersPaginated(pageable)
//    }
//
//    @PostMapping("api/v0/inferredObject")
//    JsonNode createInferredObject(@RequestBody Object object) {
//        return supplierService.getInferredSchema(object)
//    }
//}
