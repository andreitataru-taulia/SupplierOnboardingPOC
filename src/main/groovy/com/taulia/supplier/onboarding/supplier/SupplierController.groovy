package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.supplier.model.Supplier
import com.taulia.supplier.onboarding.supplier.transport.SupplierDto
import com.taulia.supplier.onboarding.supplier.transport.SupplierMapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

import javax.validation.constraints.NotNull

@Validated
@RestController
@RequestMapping("api/v0/suppliers")
class SupplierController {

    private final SupplierService supplierService
    private final SupplierMapper supplierMapper

    @Autowired
    SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService
        this.supplierMapper = supplierMapper
    }

    @Operation(summary = "Create a Supplier")
    @ApiResponses(
            value = [
                    @ApiResponse(
                            responseCode = "201",
                            description = "The Supplier has been successfully created and persisted",
                            content = [
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = SupplierDto.class))
                            ]),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
            ])
    @PostMapping
    ResponseEntity<SupplierDto> create(@RequestBody @NotNull SupplierDto supplierDto) {
        return Optional.ofNullable(supplierDto)
                .map(supplierMapper::from)
                .map(supplierService::save)
                .map(supplierMapper::to)
                .map(persistedSupplier -> new ResponseEntity<>(persistedSupplier, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST))
    }

    @Operation(summary = "Update a Supplier")
    @ApiResponses(
            value = [
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Supplier has been successfully updated and persisted",
                            content = [
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = SupplierDto.class))
                            ]),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The supplier with the transmitted id does not exists",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
            ])
    @PostMapping("{id}")
    ResponseEntity<SupplierDto> update(
            @PathVariable("id") @NotNull final UUID id,
            @RequestBody @NotNull final SupplierDto supplierDto) {
        return Optional.ofNullable(supplierDto)
                .map(supplierMapper::from)
                .flatMap(supplier -> supplierService.update(id, supplier))
                .map(supplierMapper::to)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()) as ResponseEntity<SupplierDto>
    }

    @GetMapping("{id}")
    ResponseEntity<SupplierDto> getById(@PathVariable("id") @NotNull final UUID id) {
        return supplierService
                .findById(id)
                .map(supplierMapper::to)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()) as ResponseEntity<SupplierDto>
    }

    @GetMapping
    @Operation(summary = "Retrieve a list with all of the existing Suppliers")
    @ApiResponses(
            value = [
                    @ApiResponse(
                            responseCode = "200",
                            description = "retrieved the paginated list of existing Suppliers",
                            content = [
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Supplier.class)))
                            ]),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
            ])
    // This should be handled different now using the new container classes from hateos see
    // https://stackoverflow.com/questions/56557894/does-resource-in-spring-hateoas-replace-the-dtos
    // and
    PageImpl<SupplierDto> getAllSuppliersPaginated(Pageable page) {
        var pagedResult = supplierService.getAllSuppliersPaginated(page)
        var pagedResultPageInfo = pagedResult.getPageable()
        return new PageImpl<>(
                supplierService.getAllSuppliersPaginated(page).stream().map(supplierMapper::to).toList(),
                pagedResultPageInfo,
                pagedResult.getTotalElements())
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteSupplier(@PathVariable("id") @NotNull final UUID id) {
        supplierService.delete(id)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT)
    }
}
