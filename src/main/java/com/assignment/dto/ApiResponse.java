package com.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private T data;

    public ApiResponse() {}

    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }
    
}