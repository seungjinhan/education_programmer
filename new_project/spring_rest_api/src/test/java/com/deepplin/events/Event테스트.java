package com.deepplin.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


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

	static Stream<Arguments> testFreeParams() {
		return Stream.of(
			Arguments.of(0, 0, true),
			Arguments.of(100, 0, false),
			Arguments.of(100, 0, false)
		);
	}
	@ParameterizedTest
	@MethodSource("testFreeParams")
	public void testFree(int basePrice, int maxPrice, boolean isFree){

		Event event = Event.builder()
							.basePrice(basePrice)
							.maxPrice(maxPrice)
							.build();

		event.update();

		assertThat( event.isFree()).isEqualTo(isFree);
	}

	static Stream<Arguments> testOffLineParams() {
		return Stream.of(
			Arguments.of("서울", true),
			Arguments.of("",false)
		);
	}
	@ParameterizedTest
	@MethodSource("testOffLineParams")
	public void testOffLine( String location, boolean isOffline){

		Event event = Event.builder()
							.location(location)
							.build();

		event.update();

		assertThat( event.isOffline()).isEqualTo(isOffline);
	}

}
