package com.example.creditcardapproval.service;


import com.example.creditcardapproval.entity.StatusDetails;

public interface CreditCardApprovalService {

	void updateStatus(String id, String status);

	StatusDetails saveStatus(String requestId);


}
