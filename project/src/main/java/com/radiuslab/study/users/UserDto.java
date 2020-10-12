package com.radiuslab.study.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString
public class UserDto {
    
    @NotEmpty(message = "아이디를 입력해주세요")
    private Long id;

    @NotEmpty(message = "이메일을 입력해주세요")
    @Email( message = "이메일 양식 오류")
    private String email;

    private String password;
    private String name;
   
    @Min(0)
    private Integer age;
    
    private Boolean isHost;
}
