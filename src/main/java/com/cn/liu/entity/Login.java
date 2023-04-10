package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    String name;
    String pwd;
}
