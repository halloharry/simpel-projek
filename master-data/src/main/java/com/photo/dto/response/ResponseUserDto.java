package com.photo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUserDto {
    private String email;
    private String password;
    private String name;
}
