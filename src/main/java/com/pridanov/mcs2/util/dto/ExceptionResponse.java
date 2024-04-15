package com.pridanov.mcs2.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String status;
    private String message;
}