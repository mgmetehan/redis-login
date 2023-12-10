package com.mgmetehan.redislogin.dto;

import com.mgmetehan.redislogin.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String username;
    private String password;

    public User toEntity(CreateUserDto createUserDto) {
        return User.builder()
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .build();
    }
}
