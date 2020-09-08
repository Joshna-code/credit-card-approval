package com.example.creditcardapproval.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditcardapproval.entity.StatusDetails;
import com.example.creditcardapproval.repository.CreditCardApprovalRepository;
import com.example.creditcardapproval.service.CreditCardApprovalService;

/**
 * @author intel
 *
 */
@Service
public class CreditCardApprovalServiceImpl implements CreditCardApprovalService{

	@Autowired
	CreditCardApprovalRepository creditCardApprovalRepository;
	 
	/**
	 * this method updates the status of customer credit card request
	 * @param requestId
	 * @param status
	 */
	@Override
	public void updateStatus(String requestId, String status) {
		
		Optional<StatusDetails> statusDetails = creditCardApprovalRepository.findById(requestId);
		if(statusDetails.isPresent()) {
			StatusDetails statusDetail=	statusDetails.get();
		
		
		if(status.equalsIgnoreCase("REJECTED"))
		{
			
			//update table
			statusDetail.setStatus(status);
			statusDetail.setRequestId(requestId);
			statusDetail.setMessage("missing fields");
			creditCardApprovalRepository.save(statusDetail);
		}
		if(status.equalsIgnoreCase("ACCEPTED"))
		{
			statusDetail.setStatus(status);
			statusDetail.setRequestId(requestId);
			statusDetail.setMessage("REQUEST PROOF");
			creditCardApprovalRepository.save(statusDetail);
		}
		}
	}

	/**
	 * For the inital credit card request the status will be AWAITING APPROVAL 
	 * by default until the banker review
	 */

	@Override
	public StatusDetails saveStatus(String requestId) {
		StatusDetails statusDetails = new StatusDetails();
		statusDetails.setRequestId(requestId);
		statusDetails.setStatus("AWAITING APPROVAL");
		return creditCardApprovalRepository.save(statusDetails);
	}

}
