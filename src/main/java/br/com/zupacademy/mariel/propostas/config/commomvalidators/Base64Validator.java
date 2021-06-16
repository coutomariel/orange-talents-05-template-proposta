package br.com.zupacademy.mariel.propostas.config.commomvalidators;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<IsBase64, Object> {

	@Override
	public void initialize(IsBase64 constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println("value::" + value);

		try {
			byte[] string = Base64.getDecoder().decode(value.toString());
			System.out.println(string);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
