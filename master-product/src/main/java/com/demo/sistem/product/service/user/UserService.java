package com.demo.sistem.product.service.user;

import com.demo.sistem.product.endpoint.request.LoginRequest;
import com.photo.dto.request.UserRequestDto;
import com.photo.dto.response.ResponseUserDto;
import com.photo.util.exceptionn.UserCustomExeption;

public interface UserService {
    ResponseUserDto addUser(UserRequestDto userRequestDto) throws UserCustomExeption;

    ResponseUserDto loginUser(LoginRequest loginRequest) throws UserCustomExeption;

    ResponseUserDto getUserProfile(Long userId) throws UserCustomExeption;
}
