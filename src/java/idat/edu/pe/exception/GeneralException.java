/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.exception;

/**
 *
 * @author LuisAngel
 */
public class GeneralException extends Exception {
	private String errorMessage; 
	public String getErrorMessage() {
		return errorMessage;
	}
	public GeneralException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public GeneralException() {
		super();
	}    
}
