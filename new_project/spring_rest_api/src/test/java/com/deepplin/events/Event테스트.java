package com.deepplin.events;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Event테스트 {

	/**
	 * 이벤트 도메인 Builder 테스트
	 */
	@Test
	public void event_builder_테스트() {

		Event event = Event.builder().name("스프링 REST API").description("이것은 스프링  REST API 강의").build();
		assertThat(event).isNotNull();
	}

	/**
	 * 이벤트 도메인 객체 생성 테스트
	 */
	@Test
	public void event_생성() {

		String name = "스프링 REST API";
		String desc = "이것은 스프링  REST API 강의";

		Event event = new Event();
		event.setName(name);
		event.setDescription(desc);
		assertThat(event).isNotNull();
		assertThat(event.getName()).isEqualTo(name);
		assertThat(event.getDescription()).isEqualTo(desc);
	}

}
