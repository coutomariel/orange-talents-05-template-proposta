package br.com.zupacademy.mariel.propostas.config.validationhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zupacademy.mariel.propostas.config.validationhandler.error.ErrorFieldValidation;
import br.com.zupacademy.mariel.propostas.config.validationhandler.error.ErrorsResponseDto;

@RestControllerAdvice
public class ErrorValidationHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorsResponseDto MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

		ErrorsResponseDto errorsResponseDto = new ErrorsResponseDto();

		exception.getBindingResult().getFieldErrors().forEach(error -> {
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			errorsResponseDto.getErrors().add(new ErrorFieldValidation(error.getField(), message));
		});

		return errorsResponseDto;

	}

}
