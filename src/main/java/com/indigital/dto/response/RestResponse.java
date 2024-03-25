package com.indigital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RestResponse<T> implements Serializable {

    private String status;
    private String code;
    private String message;
    private T data;


}
