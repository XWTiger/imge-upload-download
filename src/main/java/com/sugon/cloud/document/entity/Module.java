package com.sugon.cloud.document.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 顶部模块实体类
 * @author: chenxi
 * @create: 2021-07-02 11:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("顶部模块")
public class Module {

    private Integer id;

    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "备注")
    private String comment;

}
