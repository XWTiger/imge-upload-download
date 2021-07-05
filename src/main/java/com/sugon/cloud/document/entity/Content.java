package com.sugon.cloud.document.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Result;
import org.springframework.lang.NonNull;

/**
 * @description: 文档内容实体类
 * @author: chenxi
 * @create: 2021-07-02 11:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文档内容")
public class Content {

    private Integer id;

    @ApiModelProperty(value = "false表示为markdown格式，true表示为json格式", required = true)
    private Boolean type;

    @ApiModelProperty(value = "文档具体内容", required = true)
    private String content;


    @JsonProperty("model_menu_id")
    @ApiModelProperty(value = "对应菜单id", required = true)
    @NonNull
    private Integer modelMenuId;

    @ApiModelProperty(value = "0表示为未删除，1表示为已删除")
    private Integer deleted;

}
