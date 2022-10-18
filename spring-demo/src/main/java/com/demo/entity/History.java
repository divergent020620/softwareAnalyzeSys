package com.demo.entity;

import lombok.Data;

@Data
public class History {
    private String username;
    private String fileName;
    private String time;
    private String isBug;
    private String modelType;

}
