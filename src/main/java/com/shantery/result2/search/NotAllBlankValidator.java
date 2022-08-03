package com.shantery.result2.search;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

/**
 * @author s.ogata
 *
 */
public class NotAllBlankValidator implements ConstraintValidator<NotAllBlank, Object> {
	
	//アノテーションの属性名
	private String[] fields;
	private String message;
	
	//初期化処理
	@Override
	public void initialize(NotAllBlank constraintAnnotation) {
		this.fields = constraintAnnotation.fields();
		this.message = constraintAnnotation.message();
	}
	
	//バリデーション処理
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//fieldsで指定した値の取得
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		//取得した値を変数stringに格納
		for(String string: fields) {
			//fieldsの各要素の値を取得
			Object fieldValue = beanWrapper.getPropertyValue(string);
			//取得した値が一つでも空じゃなければtrueを返す
			if(fieldValue != null) {
				if(StringUtils.hasText(fieldValue.toString())) {
					return true;
				}
			}
		}
	
		//取得した値がすべてnullだったらデフォルトのメッセージと紐づけてfalseを返す
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addPropertyNode(fields[0]).addConstraintViolation();
		return false;
	
		}
}
