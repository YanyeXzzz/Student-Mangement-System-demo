package com.yanye.springbootdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page {
    private Integer pageNumber;
    private Integer pageSize;
}
