package com.userService.UserService.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String adress;
}
