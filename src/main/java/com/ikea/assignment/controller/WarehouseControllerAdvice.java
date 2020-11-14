package com.ikea.assignment.controller;
import com.ikea.assignment.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jagrati
 * The rest controller advice
 */
@ControllerAdvice(assignableTypes = WarehouseController.class)
public class WarehouseControllerAdvice {

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String serviceServiceHandler(final ServiceException ex) {
		return "{ \"message\" : \"" + ex.getMessage() + "\" }";
	}
}
