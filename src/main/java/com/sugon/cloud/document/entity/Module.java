package com.sugon.cloud.document.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("模块")
public class Module {

    private Integer id;

    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "一个模块下有多个菜单")
    private List<ModuleMenu> menus;

}
