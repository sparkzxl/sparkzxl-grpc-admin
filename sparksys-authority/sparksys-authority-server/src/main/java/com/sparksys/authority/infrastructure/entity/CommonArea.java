package com.sparksys.authority.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sparksys.database.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 地区表
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:35:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("c_common_area")
@ApiModel(value = "CCommonArea对象", description = "地区表")
public class CommonArea extends TreeEntity<CommonArea, Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "全名")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "维度")
    @TableField("latitude")
    private String latitude;

    @ApiModelProperty(value = "行政区级")
    @TableField("level")
    private String level;

    @ApiModelProperty(value = "数据来源")
    @TableField("source_")
    private String source;

}
