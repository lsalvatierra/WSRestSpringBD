/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.bean.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author LuisAngel
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
                String[] valError = ex.getMessage().split("/");
		error.setErrorCode(Integer.parseInt(valError[0]));
		error.setMessage(valError[1]);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}    
}
