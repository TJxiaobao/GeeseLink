package com.message.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
// @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    public static <T> Result<T> fail(String msg, T data) {
        return new Result<>(500, msg, data);
    }
}