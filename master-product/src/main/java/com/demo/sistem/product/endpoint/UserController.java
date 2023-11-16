package com.demo.sistem.product.endpoint;

import com.demo.sistem.product.endpoint.request.LoginRequest;
import com.demo.sistem.product.service.user.UserService;
import com.photo.dto.request.UserRequestDto;
import com.photo.dto.response.ResponseUserDto;
import com.photo.util.IResultDto;
import com.photo.util.core.APIResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    public IResultDto<ResponseUserDto> addUser(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(userService.addUser(userRequestDto));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

    @PostMapping("/login")
    public IResultDto<ResponseUserDto> userLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(userService.loginUser(loginRequest));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

    @GetMapping("/{user-id}")
    public IResultDto<ResponseUserDto> getUserProfile(@PathVariable("user-id") Long userId, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(userService.getUserProfile(userId));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

}
