package com.chemaev.dto;

import com.chemaev.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private int id;
    private String name;
    private String email;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
