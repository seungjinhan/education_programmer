package com.deepplin.events;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventController테스트 {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("Event 생성 테스트")
	public void event_생성_테스트() throws Exception {
		
		EventDto event = EventDto.builder()
								.name("Hansj")
								.description("this is desc")
								.beginEnrollmentDateTime( LocalDateTime.of(2020, 10,7,14,21))
								.closeEnrollmentDateTime( LocalDateTime.of(2020, 10,8,14,21))
								.beginEventDateTime(LocalDateTime.of(2020, 10,9,14,21))
								.endEventDateTime(LocalDateTime.of( 2020, 10,10, 14,21))
								.basePrice(100)
								.maxPrice(200)
								.limitOfEnrollment(100)
								.location("강남역")
								.build();
		
		this.mockMvc.perform( 
								post("/api/events/")
								.contentType( MediaType.APPLICATION_JSON)
								.accept(MediaTypes.HAL_JSON)	
								.content( objectMapper.writeValueAsString( event))
							)	
							.andDo( print())
							.andExpect( status().isCreated())
							.andExpect(jsonPath("id").exists())
							.andExpect( header().exists( HttpHeaders.LOCATION))
							.andExpect( header().string( HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON.toString()))
							.andExpect( jsonPath("free").value( Matchers.is(false)))
							.andExpect( jsonPath("offline").value( Matchers.is(true)))
							.andExpect( jsonPath("eventStatus").value( EventStatus.DRAFT.name()))
							;
	}
	

	@Test
	@DisplayName("Event 생성 Bad Request 테스트")
	public void event_생성_BAD_REQUEST_테스트() throws Exception {
		
		Event event = Event.builder()
								.id(100)
								.name("Hansj")
								.description("this is desc")
								.beginEnrollmentDateTime( LocalDateTime.of(2020, 10,7,14,21))
								.closeEnrollmentDateTime( LocalDateTime.of(2020, 10,8,14,21))
								.beginEventDateTime(LocalDateTime.of(2020, 10,9,14,21))
								.endEventDateTime(LocalDateTime.of( 2020, 11,10, 14,21))
								.basePrice(100)
								.maxPrice(200)
								.limitOfEnrollment(100)
								.location("강남역")
								.free(true)
								.offline(false)
								.eventStatus( EventStatus.PUBLISHED)
								.build();
		
		this.mockMvc.perform( 
								post("/api/events/")
								.contentType( MediaType.APPLICATION_JSON)
								.accept(MediaTypes.HAL_JSON)	
								.content( objectMapper.writeValueAsString( event))
							)	
							.andDo( print())
							.andExpect( status().isBadRequest())
							;
							
	}
	
	@Test
	@DisplayName("Event 생성 Bad Request - 공백입력")
	public void event_생성_bad_request_empty_input_테스트() throws Exception {
		
		EventDto eventDto = EventDto.builder().build();
		
		this.mockMvc.perform( 
					post("/api/events")
					.contentType( MediaType.APPLICATION_JSON)
					.content( this.objectMapper.writeValueAsString( eventDto))				
					)
					.andExpect( status().isBadRequest());
	}
	
	@Test
	@DisplayName("Event 생성 Bad Request - 잘못된 입력")
	public void event_생성_bad_request_wrong_input_테스트() throws Exception {
		
		EventDto eventDto = EventDto.builder()
										.name("test")
										.description("this is desc")
										.beginEnrollmentDateTime( LocalDateTime.of(2020, 10,9,14,21))
										.closeEnrollmentDateTime( LocalDateTime.of(2020, 10,8,14,21))
										.beginEventDateTime(LocalDateTime.of(2020, 10,11,14,21))
										.endEventDateTime(LocalDateTime.of( 2020, 10,10, 14,21))
										.basePrice(100000)
										.maxPrice(200)
										.limitOfEnrollment(100)
										.location("강남역")
										.build();
		
		this.mockMvc.perform( 
						post("/api/events")
						.contentType( MediaType.APPLICATION_JSON)
						.content( this.objectMapper.writeValueAsString( eventDto))				
					)
					.andDo(print())
					.andExpect( status().isBadRequest())
					.andExpect( jsonPath("$[0].objectName").exists())
					.andExpect( jsonPath("$[0].defaultMessage").exists())
					.andExpect( jsonPath("$[0].code").exists())
					;
	}
	
}
