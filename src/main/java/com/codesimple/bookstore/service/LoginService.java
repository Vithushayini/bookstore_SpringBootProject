package com.codesimple.bookstore.service;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.dto.LoginRequestDTO;
import com.codesimple.bookstore.dto.SignUpRequestDTO;
import com.codesimple.bookstore.entity.User;
import com.codesimple.bookstore.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse=new APIResponse();

        //validation

        //dto to entity
        User userEntity=new User();
        userEntity.setName(signUpRequestDTO.getName());
        userEntity.setEmailId(signUpRequestDTO.getEmailId());
        userEntity.setActive(Boolean.TRUE);
        userEntity.setGender(signUpRequestDTO.getGender());
        userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        userEntity.setPassword(signUpRequestDTO.getPassword());

        //store entity
        userEntity=userRepository.save(userEntity);

        //return
        apiResponse.setData(userEntity);

        return apiResponse;
    }

    public APIResponse login(LoginRequestDTO loginRequestDTO) {

        APIResponse apiResponse=new APIResponse();

        //validation

        //verify user exist with given email and password
        User user=userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(),loginRequestDTO.getPassword());

        //response
        if(user==null){
            apiResponse.setData("User login failed");
        }
        else {
            apiResponse.setData("user logged in");
        }

        return apiResponse;
    }
}
