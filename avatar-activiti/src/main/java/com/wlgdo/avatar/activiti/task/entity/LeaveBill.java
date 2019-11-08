package com.wlgdo.avatar.activiti.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 请假流程
 *
 * @author 冷冷
 * @date 2018-09-27 15:20:44
 */
@Data
@TableName("oa_leave_bill")
@ApiModel(value = "隐患总表参数")
@EqualsAndHashCode(callSuper = true)
public class LeaveBill extends Model<LeaveBill> {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer leaveId;
    /**
     * 申请人
     */
    private String username;
    /**
     * 天数
     */
    private Integer days;
    /**
     * 备注
     */
    private String content;
    /**
     * 0-未提交,1-未审核,2-批准,9-驳回
     */
    private String state;
    /**
     * 提交时间
     */
    private LocalDateTime leaveTime;
    /**
     * 提交时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 租户ID
     */
    private Integer tenantId;

}
