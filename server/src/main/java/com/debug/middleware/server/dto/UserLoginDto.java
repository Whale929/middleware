package com.debug.middleware.server.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: AlexHsiao
 * @Date: 2021/1/14 上午10:10
 */

@Data
@ToString
public class UserLoginDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    private Integer userId;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserId() {
        return userId;
    }
}
