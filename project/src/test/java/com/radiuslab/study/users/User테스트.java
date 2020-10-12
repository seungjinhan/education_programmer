package com.radiuslab.study.users;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class User테스트 {
    
    @Test
    public void user_builder_생성_테스트(){

        User user = User.builder().build();
        assertThat(user).isNotNull();
    }

    @Test
    public void user_객체생성_테스트(){
        
        User user = new User();
        assertThat(user).isNotNull();
    }

}
