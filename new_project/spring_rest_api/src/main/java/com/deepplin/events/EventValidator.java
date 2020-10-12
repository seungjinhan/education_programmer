package com.deepplin.events;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class EventValidator {

	public void validate( EventDto eventDto, Errors errors) {
		if( eventDto.getMaxPrice() < eventDto.getBasePrice() && eventDto.getMaxPrice() != 0) {
			
			errors.rejectValue("basePrice", "wrong value" ,"BasePrice is wrong");
			errors.rejectValue("maxPrice", "wrong value" ,"MaxPrice is wrong");
			errors.reject("wrongPrices" , "Prices are wrong");
		}
		
		LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
		LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();
		LocalDateTime closeEnrollmentDateTime = eventDto.getCloseEnrollmentDateTime();
		LocalDateTime beginEnrollmentDateTime = eventDto.getBeginEnrollmentDateTime();
		
		if( endEventDateTime.isBefore(beginEventDateTime) 
				|| endEventDateTime.isBefore(closeEnrollmentDateTime)
				|| endEventDateTime.isBefore(beginEnrollmentDateTime)
				) {
			errors.rejectValue("endEventDateTime", "wrong value", "endEventDateTime");			
		}	
		
	}
}
