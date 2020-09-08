package com.example.creditcardapproval.controller;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.example.creditcardapproval.entity.StatusDetails;
import com.example.creditcardapproval.repository.CreditCardApprovalRepository;
import com.example.creditcardapproval.service.CreditCardApprovalService;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardApprovalControllerTest {

	@InjectMocks
	CreditCardApprovalController creditCardApprovalController;
	
	@Mock
	CreditCardApprovalService creditCardApprovalService;
	
	@Mock
	CreditCardApprovalRepository creditCardApprovalRepository;
	
	@Mock
	RestTemplate restTemplate;

	@Test
	public void testApproveCreditCard() {
		
		RestTemplate restTemplate = new RestTemplate();
		creditCardApprovalService.updateStatus("1", "ACCEPTED");
		String requestId = "1";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestBody = new HttpEntity<>(requestId, headers);
		String url = "http://localhost:8100/api/v1/saveCustomerStatus/"+requestId;
		 restTemplate.put(url, requestBody);
		creditCardApprovalController.approveCreditCard("ACCEPTED", "1");
		assertNotNull(requestBody);
		
	}
	
	@Test
	public void testSaveCreditCard() {
		
		StatusDetails status = new StatusDetails();
		status.setRequestId("1");
		status.setStatus("AWAITING APPROVAL");
		when(creditCardApprovalService.saveStatus(anyString())).thenReturn(status);
		creditCardApprovalRepository.save(status);
		creditCardApprovalController.saveCreditCard("1");
		assertEquals("AWAITING APPROVAL", status.getStatus());
	}


}
