package com.example.csiauth.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private String title;
    private Integer status;
    @Setter
    private String mensage;

}
