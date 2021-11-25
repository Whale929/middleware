package com.debug.middleware.server.controller.rabbitmq;

import com.debug.middleware.api.enums.StatusCode;
import com.debug.middleware.api.response.BaseResponse;
import com.debug.middleware.server.dto.UserLoginDto;
import com.debug.middleware.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: AlexHsiao
 * @Date: 2021/1/14 上午10:12
 */
@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String prefix="user";

    @Autowired
    private UserService userService;

    @RequestMapping(value = prefix+"/login",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse login(@RequestBody @Validated UserLoginDto dto, BindingResult result){
        if(result.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try{
            Boolean res=userService.login(dto);
            if (res){
                response=new BaseResponse(StatusCode.Success.getCode(),"登陆成功");
            }
            else {
                response=new BaseResponse(StatusCode.Fail.getCode(),"登陆失败");
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
