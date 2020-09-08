package com.example.creditcardapproval.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * This is the main Entity where the status details will be stored
 *
 */
@Entity
@Table(name = "STATUS_DETAILS")
@Data
public class StatusDetails {

	@Id
	@ApiModelProperty(notes = "the order id")
	@Column(name = "request_Id")
	private String requestId;

	@ApiModelProperty(notes = "status of the request")
	@Column(name = "status")
	private String status;
	
	@ApiModelProperty(notes ="reason for approve/reject")
	@Column(name = "message")
	private String message;
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
