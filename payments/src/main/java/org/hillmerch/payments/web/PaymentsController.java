package org.hillmerch.payments.web;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hillmerch.payments.client.PaymentsControllerDefinition;
import org.hillmerch.payments.client.dto.PaymentDTO;
import org.hillmerch.payments.web.assembler.PaymentAssembler;
import org.hillmerch.payments.web.engine.PaymentProducer;
import org.hillmerch.payments.web.service.PaymentsService;

@RestController
@RequestMapping("/payments")
public class PaymentsController  implements PaymentsControllerDefinition {

	private static final Logger logger = LoggerFactory.getLogger( PaymentProducer.class);
	private final PaymentsService paymentsService;
	private final PaymentAssembler paymentAssembler;
	private final PagedResourcesAssembler<PaymentDTO> pagedResourcesAssembler;

	public PaymentsController(
			PaymentsService paymentsService,
			PaymentAssembler paymentAssembler,
			PagedResourcesAssembler<PaymentDTO> pagedResourcesAssembler) {
		this.paymentsService = paymentsService;
		this.paymentAssembler = paymentAssembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}


	@Override
	public ResponseEntity<PagedModel<EntityModel<PaymentDTO>>> getAllPayments(
			Integer pageNumber, Integer pageSize, String sortKey) {

		logger.info( "Searching all payments" );

		List<Sort.Order> orders = new ArrayList<>();

		String[] sortArray = sortKey.split( "," );
		for(int it=0; it < sortArray.length; it=it+2){
			orders.add(new Sort.Order(Sort.Direction.valueOf( sortArray[it+1].toUpperCase() ) , sortArray[it]));
		}

		Pageable pageable = PageRequest.of( pageNumber, pageSize, Sort.by( orders));

		Page<PaymentDTO> entityModelList = this.paymentsService.findAll( pageable );
		return ResponseEntity.status( HttpStatus.OK ).body( pagedResourcesAssembler.toModel( entityModelList, paymentAssembler ) );


	}

	@Override
	public ResponseEntity<EntityModel<PaymentDTO>> newPayment(@Valid PaymentDTO newPaymentDTO) throws JsonProcessingException {
		logger.info( "Adding payment: " + newPaymentDTO );
		PaymentDTO paymentDTO = paymentsService.save( newPaymentDTO );

		EntityModel<PaymentDTO> paymentDTOEntityModel = paymentAssembler.toModel( paymentDTO );

		return ResponseEntity.status( HttpStatus.OK ).body( paymentDTOEntityModel );
	}
}
