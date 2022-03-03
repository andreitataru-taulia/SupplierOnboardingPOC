package com.taulia.supplier.onboarding.postgresql.supplier

//import com.taulia.supplier.onboarding.supplier.transport.SupplierDto
//import com.taulia.supplier.onboarding.supplier.transport.SupplierMapper
//import io.swagger.v3.oas.annotations.Operation
//import io.swagger.v3.oas.annotations.media.Content
//import io.swagger.v3.oas.annotations.media.Schema
//import io.swagger.v3.oas.annotations.responses.ApiResponse
//import lombok.RequiredArgsConstructor
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController("api/suppliers")
//@RequiredArgsConstructor
//class SupplierController {
//
//    private SupplierService supplierService
//    private SupplierMapper supplierMapper
//
//    SupplierDto createSupplier(SupplierDto supplierDto){
//      return supplierDto.with{supplierMapper::from}
//                .with {supplierService::save}
//        .with {supplierMapper::to}
//    }
//
//
//    @Operation(summary = "Retrieve a list with all of the existing Suppliers")
//    @ApiResponse(responseCode = "200", description = "The list of all existing Suppliers",
//            content = @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = Supplier.class)))
//
//    @GetMapping
//    List<SupplierDto> getAllSuppliers() {
//        return supplierService.getAllSuppliers()
//                .stream()
//                .map(supplierMapper::to)
//                .toList()
//    }
//}
