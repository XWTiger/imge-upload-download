package com.sugon.cloud.document.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("菜单")
public class ModuleMenu {

    private Integer id;

    @JsonProperty("module_id")
    @ApiModelProperty(value = "所属模块", required = true)
    private Integer moduleId;

    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;

    @JsonProperty("parent_id")
    @ApiModelProperty(value = "父菜单")
    private Integer parentId;

    @JsonProperty("module_menus")
    @ApiModelProperty(value = "下面的子菜单", hidden = true)
    private List<ModuleMenu> moduleMenus;

}
