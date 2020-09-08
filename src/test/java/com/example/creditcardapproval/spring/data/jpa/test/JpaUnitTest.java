package com.example.creditcardapproval.spring.data.jpa.test;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.creditcardapproval.CreditCardApprovalApplication;
import com.example.creditcardapproval.entity.StatusDetails;
import com.example.creditcardapproval.repository.CreditCardApprovalRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditCardApprovalApplication.class)
public class JpaUnitTest {

	@Autowired
	private CreditCardApprovalRepository creditCardApprovalRepository;
	
	
	@Test
	public void testCreditCardApprovalRepository() {
		
		StatusDetails status = new StatusDetails();
		status.setStatus("ACCEPTED");
		status.setRequestId("1");
		creditCardApprovalRepository.save(status);
		Assert.assertNotNull(status.getRequestId());
		
	}

}
