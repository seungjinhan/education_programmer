package com.radiuslab.study.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode( of = "id")
@Entity(name = "users")
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
    private Integer age;
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
