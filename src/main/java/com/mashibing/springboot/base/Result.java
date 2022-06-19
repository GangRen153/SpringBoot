package com.mashibing.springboot.base;

import java.util.Map;

public class Result<T> {
    private Integer code;
    private Boolean success;
    private String message;
    private T data;
    private Map<String, Object> map;

    public Result(Boolean success) {
        this.success = success;
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static Result newResult() {
        return new Result((Boolean)null);
    }

    public static Result newResult(Boolean success) {
        return new Result(success);
    }

    public Result put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMap(final Map<String, Object> map) {
        this.map = map;
    }

}
