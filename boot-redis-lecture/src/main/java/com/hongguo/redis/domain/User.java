package com.hongguo.redis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author hongguo_cheng
 * @date 2021/7/28
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -2317573920583105700L;

    private Long id;

    private String name;

    private Integer age;
}
