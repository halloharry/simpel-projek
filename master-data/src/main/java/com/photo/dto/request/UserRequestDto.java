package com.photo.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
    private String name;
}
