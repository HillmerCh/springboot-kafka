package org.hillmerch.payments.client;


import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.hillmerch.payments.client.dto.PaymentDTO;


public interface PaymentsControllerDefinition {


	@Operation(
			summary = "Gets all payments in the system",
			description = "Searches all payments, the result is paged",
			operationId = "getAllPayments",
			parameters = {

					@Parameter(name = "pageNumber", in = ParameterIn.QUERY,
							schema = @Schema(type = "integer", defaultValue = "0", example = "0"),
							description = "Page number. Notice that the first page starts with index 0."),
					@Parameter(name = "pageSize", in = ParameterIn.QUERY,
							schema = @Schema(type = "integer", defaultValue = "10", example = "10"), description = "Page size"),
					@Parameter(name = "sortKey", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")),
							description = "Sorting criteria in the format: property,asc|dec. "
									+ "Default sort order is ascending."
									+ " Multiple sort criteria are supported.",
							example = "id,desc,dateOfPayment,asc")
			})
	@GetMapping
	ResponseEntity<PagedModel<EntityModel<PaymentDTO>>> getAllPayments(@RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
																	  @RequestParam(value="pageSize", defaultValue="10") Integer pageSize,
																	  @RequestParam(value="sortKey", defaultValue="id,asc") String sortKey);



	@Operation(summary = "Adds a payment",
			description = "Adds a new payment",
			operationId = "newPayment")
	@PostMapping
	ResponseEntity<EntityModel<PaymentDTO>> newPayment(@Valid @RequestBody PaymentDTO newPaymentDTO) throws JsonProcessingException;

}
