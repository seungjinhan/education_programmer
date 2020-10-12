package com.radiuslab.study.users;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserInsertDto {
    
    @NotEmpty(message = "이메일을 입력해주세요")
    private String email;
    @NotEmpty(message = "패스워드를 입력해주세요")
    private String password;
    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @Min(0)
    @NotEmpty(message = "나이를 입력해주세요")
    private Integer age;
    
    @NotEmpty(message = "이메일을 입력해주세요")
    private Boolean isHost;
    
    private Boolean isAdult;

    public void update(){

        if( age < 20){
            this.isAdult = true;
        }
        else{
            this.isAdult = false;
        }
    }
}
