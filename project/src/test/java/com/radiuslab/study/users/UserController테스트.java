package com.radiuslab.study.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.radiuslab.SpringMockMvcTestBase;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class UserController테스트 extends SpringMockMvcTestBase {

    @Test
    public void 회원가입_필수항목누락_테스트() throws JsonProcessingException, Exception {

        UserInsertDto userDto = UserInsertDto.builder()
                        .name("hansj")
                        .password("1234")
                        .email("han@naver.com")
                        //.age(20)
                        .isHost(false)
                        .build();

        this.mockMvc.perform( 
                        post("/api/users")
                        .contentType( MediaType.APPLICATION_JSON)
                        .content( this.objectMapper.writeValueAsString(userDto))
                     )
                    .andDo(print())
                    .andExpect( status().isBadRequest());
    }

    @Test
    public void 회원가입_필수항목_테스트() throws JsonProcessingException, Exception {

        UserInsertDto userDto = UserInsertDto.builder()
                        .name("hansj")
                        .password("1234")
                        .email("han@naver.com")
                        .age(20)
                        .isHost(false)
                        .build();

        this.mockMvc.perform( 
                        post("/api/users")
                        .contentType( MediaType.APPLICATION_JSON)
                        .content( this.objectMapper.writeValueAsString(userDto))
                     )
                    .andDo(print())
                    .andExpect( status().isCreated());
    }

}
