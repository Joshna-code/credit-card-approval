package com.example.creditcardapproval.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.creditcardapproval.entity.StatusDetails;
import com.example.creditcardapproval.service.CreditCardApprovalService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the controller class for banker to approve the credit card
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")

public class CreditCardApprovalController {

	private CreditCardApprovalService creditCardApprovalService;
	
	private RestTemplate restTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(CreditCardApprovalController.class);	
	 @Autowired
	    public CreditCardApprovalController(CreditCardApprovalService creditCardApprovalService, RestTemplate restTemplate){
	        this.creditCardApprovalService = creditCardApprovalService;
	        this.restTemplate = restTemplate;
	    }
	 
	 /**
	  * This endpoint is used to update the status of credit card request
	  * @param status
	  * @param requestId
	  */
	 @ApiOperation(value = "update the status", notes = "This endpoint is used to update the status of credit card request")
		@PutMapping("/customer/{requestId}")
		public void approveCreditCard(
				@RequestParam(value = "status") String status,
				@PathVariable(value = "requestId", required = true) String requestId)
		{
		 
			 creditCardApprovalService.updateStatus(requestId,status);
			 logger.info("The credit card request is reviewed with the status {}", status);
			 HttpEntity<String> requestBody = new HttpEntity<>(status);
				String url = "http://localhost:8100/api/v1/saveCustomerStatus/"+requestId;
				restTemplate.put(url, requestBody);
		}
	 
	 /**
	  * This endpoint saves the detail of the customer with request status as AWAITING APPROVAL
	  * @param requestId
	  * @return
	  */
	 @ApiOperation(value = "save credit card", notes = "This endpoint save details of the customer")
		@PostMapping("/customer")
		public ResponseEntity<String> saveCreditCard(
				@RequestBody String requestId)
				
		{
		StatusDetails statusDetails=creditCardApprovalService.saveStatus(requestId);
		logger.info("customer details saved successfully");
			return ResponseEntity.ok(statusDetails.getStatus());
			
		}
		
	 
}
