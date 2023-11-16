package com.demo.sistem.product.mapper;

import com.photo.dto.response.ResponseUserDto;
import com.photo.model.User;
import com.photo.util.mapper.ADATAMapper;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper extends ADATAMapper<User, ResponseUserDto> {
    @Override
    public ResponseUserDto convert(User user) {
        return ResponseUserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
