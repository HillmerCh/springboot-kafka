package org.hillmerch;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hillmerch.payments.client.dto.PaymentDTO;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SpringbootKafkaApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private MockMvc mockMvc;

	private final static String TEST_USER_ID = "user-id-123";

	private ObjectMapper OBJECT_MAPPER;

	@BeforeEach
	public void setup() {
		OBJECT_MAPPER = new  ObjectMapper();
		OBJECT_MAPPER.registerModule(new Jackson2HalModule());
		OBJECT_MAPPER.registerModule(new JavaTimeModule());

	}

	@Test
	@DisplayName("Getting all Payments")
	void getAllPayments() throws Exception {

		MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.get( "/payments" )
													   .with( user( TEST_USER_ID ) )//Pretend we have a valid token
													   .with( csrf() )//Pretend we have a valid token
		)
				.andExpect( status().isOk() )
				.andReturn();

		PagedModel<List<PaymentDTO>> pagedModel = OBJECT_MAPPER.readValue( mvcResult.getResponse().getContentAsString(), PagedModel.class);

		assertTrue( pagedModel.getContent().size() >= 0 );
	}

	@Test
	@DisplayName("Posting a new Payment")
	void addPayment() throws Exception {

		String jsonContent = OBJECT_MAPPER.writeValueAsString( new PaymentDTO()
																	   .orderId( "123" )
																	   .dateOfPayment( LocalDateTime.now() )
																	   .value( 100L ));

		mockMvc.perform( MockMvcRequestBuilders.post( "/payments" )
								 .content( jsonContent )
								 .contentType( MediaType.APPLICATION_JSON )
								 .with( user( TEST_USER_ID ) )//Pretend we have a valid token
								 .with( csrf() )//Pretend we have a valid token
		).andExpect( status().isOk() );

	}
}
