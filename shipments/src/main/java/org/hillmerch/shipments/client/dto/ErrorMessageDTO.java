package org.hillmerch.shipments.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name="ErrorMessage" , description = "Errors detail response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageDTO {
	private String message;
	private String resource;
	private String status;
	private List<ErrorMessageDTO> errorMessageDTOList;

	public ErrorMessageDTO() {
	}

	public ErrorMessageDTO(String resource, String status, String message) {
		this.resource = resource;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ErrorMessageDTO> getErrorMessageDTOList() {
		return errorMessageDTOList;
	}

	public void setErrorMessageDTOList(List<ErrorMessageDTO> errorMessageDTOList) {
		this.errorMessageDTOList = errorMessageDTOList;
	}

	@Override
	public String toString() {
		return "ErrorMessageDTO{" +
				"message='" + message + '\'' +
				", resource='" + resource + '\'' +
				", status='" + status + '\'' +
				", errorMessageDTOList=" + errorMessageDTOList +
				'}';
	}
}
