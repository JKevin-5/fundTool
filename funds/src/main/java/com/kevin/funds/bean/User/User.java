package com.kevin.funds.bean.User;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    private String userNo;
    private String userName;
    private String userPassWord;
    private String emailAddress;
}
