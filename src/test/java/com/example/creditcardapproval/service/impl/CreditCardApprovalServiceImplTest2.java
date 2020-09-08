package com.example.creditcardapproval.service.impl;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.creditcardapproval.entity.StatusDetails;
import com.example.creditcardapproval.repository.CreditCardApprovalRepository;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardApprovalServiceImplTest2 {

	@InjectMocks
	CreditCardApprovalServiceImpl creditCardApprovalServiceImpl;
	
	@Mock
	CreditCardApprovalRepository creditCardApprovalRepository;

	@Test
	public void testUpdateStatus_ACCEPTED() {
		
		StatusDetails status = new StatusDetails();
		status.setStatus("ACCEPTED");
		when(creditCardApprovalRepository.findById(any())).thenReturn(Optional.of(status));
		creditCardApprovalServiceImpl.updateStatus("3", "ACCEPTED");
		assertEquals("3", status.getRequestId());
	}


	@Test
	public void testUpdateStatus_REJECTED() {
		
		StatusDetails status = new StatusDetails();
		status.setStatus("REJECTED");
		when(creditCardApprovalRepository.findById(any())).thenReturn(Optional.of(status));
		creditCardApprovalServiceImpl.updateStatus("3", "REJECTED");
		assertEquals("3", status.getRequestId());
	}
	
	@Test
	public void testSaveStatus() {
		
		StatusDetails status = new StatusDetails();
		status.setStatus("AWAITING APPROVAL");
		status.setRequestId("1");
		creditCardApprovalRepository.save(status);
		creditCardApprovalServiceImpl.saveStatus("1");
		assertEquals("AWAITING APPROVAL", status.getStatus());
	}
}
