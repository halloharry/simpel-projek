package com.demo.sistem.product.service.user.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.demo.sistem.product.endpoint.request.LoginRequest;
import com.demo.sistem.product.mapper.UserResponseMapper;
import com.demo.sistem.product.service.user.UserService;
import com.photo.dao.UserRepository;
import com.photo.dto.request.UserRequestDto;
import com.photo.dto.response.ResponseUserDto;
import com.photo.model.User;
import com.photo.util.exceptionn.UserCustomExeption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    @Value("${data.value.email.regexp}")
    private String emailRgx;
    @Value("${data.value.pass.regexp}")
    private String passRgx;

    private static void delay() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public ResponseUserDto getUserProfile(Long userId) throws UserCustomExeption {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserCustomExeption("user tidak di temukan"));

        return userResponseMapper.convert(user);
    }

    @Override
    public ResponseUserDto addUser(UserRequestDto userRequestDto) throws UserCustomExeption {

        boolean emailTrue = Pattern.matches(emailRgx, userRequestDto.getEmail());
        boolean passwordTrue = Pattern.matches(passRgx, userRequestDto.getPassword());
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, userRequestDto.getPassword().toCharArray());
        if (emailTrue && passwordTrue) {
            List<User> existUser = userRepository.findAllByEmail(userRequestDto.getEmail()).stream().filter(x -> !x.getIsDeleted()).collect(Collectors.toList());
            if (!existUser.isEmpty()) {
                throw new UserCustomExeption("user sudah ada");
            }
            User user = new User();
            user.setName(userRequestDto.getName());
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(bcryptHashString);
            user.setIsDeleted(false);
            User userSaved = userRepository.save(user);
            return userResponseMapper.convert(userSaved);
        } else {
            throw new UserCustomExeption("periksa kembali email dan password");
        }
    }

    @Override
    public ResponseUserDto loginUser(LoginRequest loginRequest) throws UserCustomExeption {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null) {
            String password = loginRequest.getPassword();
            String password2 = user.getPassword();
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), password2);
            log.info("cek result : " + result.verified);
            if (result.verified) {
                return userResponseMapper.convert(user);
            }
        } else {
            throw new UserCustomExeption("terjadi kesalahan, cek email dan password");
        }
        throw new UserCustomExeption("user tidak ditemukan");
    }

}
