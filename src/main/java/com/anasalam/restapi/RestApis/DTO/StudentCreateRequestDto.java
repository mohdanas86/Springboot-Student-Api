package com.anasalam.restapi.RestApis.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequestDto {
    private String name;
    private String email;
    private String password;
}
