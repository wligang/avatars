package com.wlgdo.avatar.service.users.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参与者
 * </p>
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @since 2019-05-10
 */
@Data
@Accessors(chain = true)
public class TActor{

    private static final long serialVersionUID=1L;

    @TableId("Id")
    private String Id;

    /**
     * 微信的openid
     */
    private String openid;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 机构ID
     */
    private String orgId;

    /**
     * 姓名
     */
    private String name;

    private String nickName;

    /**
     * 性别：0未知，1：男，2：女
     */
    private String gender;

    /**
     * 用户头像uri
     */
    private String headImg;

    /**
     * 微信信息使用标准json格式存储
     */
    private String wxBody;

    /**
     * 雇员号码
     */
    private String employeeNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 参与者状态0:未参与，1:手机未认证，2：正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "TActor{" +
                "Id='" + Id + '\'' +
                ", openid='" + openid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", orgId='" + orgId + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                ", headImg='" + headImg + '\'' +
                ", wxBody='" + wxBody + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
