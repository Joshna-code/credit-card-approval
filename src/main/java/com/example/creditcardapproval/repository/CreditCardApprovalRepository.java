package com.example.creditcardapproval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.creditcardapproval.entity.StatusDetails;

@Repository
public interface CreditCardApprovalRepository extends JpaRepository<StatusDetails, String> {

}
