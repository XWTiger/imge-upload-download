package com.sugon.cloud.document.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 菜单实体类
 * @author: chenxi
 * @create: 2021-07-02 11:23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("菜单")
public class ModuleMenu {

    private Integer id;

    @JsonProperty("module_id")
    @ApiModelProperty(value = "顶部模块id", required = true)
    private Integer moduleId;

    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;

    @JsonProperty("parent_menu")
    @ApiModelProperty(value = "父菜单")
    private ModuleMenu parentMenu;

}
