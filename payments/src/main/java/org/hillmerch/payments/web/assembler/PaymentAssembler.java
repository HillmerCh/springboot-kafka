package org.hillmerch.payments.web.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.hillmerch.payments.client.dto.PaymentDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PaymentAssembler implements RepresentationModelAssembler<PaymentDTO, EntityModel<PaymentDTO>> {


	@Override
	public EntityModel<PaymentDTO> toModel(PaymentDTO paymentDTO) {
		return EntityModel.of(
				paymentDTO
				//linkTo(methodOn( PaymentsController.class).get( paymentDTO.getId())).withSelfRel(),
				//linkTo( methodOn( PaymentsController.class ).getAllPayments( 0, 10, "id,asc" ) ).withRel( "payments" )
		);
	}
}
