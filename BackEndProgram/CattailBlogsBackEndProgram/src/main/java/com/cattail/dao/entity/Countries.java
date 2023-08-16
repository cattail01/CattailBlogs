package com.cattail.dao.entity;

import lombok.Data;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/16 12:44
 * @description
 */
@Data  // lombok注解
public class Countries {
    private String countryId;
    private String countryName;
    private Integer regionId;
}
