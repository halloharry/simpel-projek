package com.demo.sistem.product.mapper;

import com.photo.dto.response.ResponseUserDto;
import com.photo.util.mapper.ADATAMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailMapper extends ADATAMapper<UserDetails, ResponseUserDto> {
    @Override
    public ResponseUserDto convert(UserDetails userDetails) {
        return ResponseUserDto.builder()
                .name(userDetails.getUsername())
                .password(userDetails.getPassword())
                .build();
    }
}
