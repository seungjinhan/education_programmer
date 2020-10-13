package com.deepplin.events;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EventValidator eventValidator;
	
	@PostMapping
	public ResponseEntity createEvent( @RequestBody @Valid EventDto eventDto, Errors errors) {
		
		if( errors.hasErrors()) {
			return ResponseEntity.badRequest().body( errors);
		}
		
		this.eventValidator.validate(eventDto, errors);
		if( errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors);
		}
		
		Event event = this.modelMapper.map(eventDto, Event.class);
		
		event.update();
		
		Event newEvent = this.eventRepository.save( event);
		
		URI uri = linkTo( EventController.class).slash( newEvent.getId()).toUri();
		
		return ResponseEntity.created( uri).body( event);
	}
}
