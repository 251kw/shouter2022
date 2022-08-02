package com.shantery.result2.search;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

public class NotAllBlankValidator implements ConstraintValidator<NotAllBlank, Object> {
	
	private String[] fields;
	private String message;
	
	public void initialize(NotAllBlank constraintAnnotation) {
		this.fields = constraintAnnotation.fields();
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		for(String string: fields) {
			Object fieldValue = beanWrapper.getPropertyValue(string);
			if(fieldValue != null) {
				if(StringUtils.hasText(fieldValue.toString())) {
					return true;
				}
			}
		}
	
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addPropertyNode(fields[0]).addConstraintViolation();
		return false;
	
		}
}
