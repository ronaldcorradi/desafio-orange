package br.com.desafio.conf.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.desafio.conf.validation.dto.ErrorFormDTO;

@RestControllerAdvice
public class ErrorValidationHandler {
	
	@Autowired
	MessageSource messageSoruce;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDTO> handle(MethodArgumentNotValidException exception) {
		
		
		List<ErrorFormDTO> errors = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSoruce.getMessage(e, LocaleContextHolder.getLocale());
			ErrorFormDTO error = new ErrorFormDTO(e.getField(),message);
			errors.add(error);
		});
		return errors;
		
	}
	
}
