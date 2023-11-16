package com.demo.sistem.product.endpoint.request;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
