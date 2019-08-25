package com.wlgdo.avatar.service.users.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @since 2019-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_users")
public class Users implements Serializable {

    /**
     * 外部第三方ID
     */
    private String openId;

    /**
     * 开发平台联合ID
     */
    private String unionId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别（1. 男 0. 女）
     */
    private Integer sex;

    /**
     * 生日
     */
    private String birth;

    /**
     * 用户头像
     */
    private String headimgurl;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 坐标
     */
    private String location;

    /**
     * 定位详细地址
     */
    private String localAddr;

    /**
     * 城市
     */
    private Integer cityId;

    /**
     * 用户所在城市名称
     */
    private String cityName;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 可用状态（1. 可用 2.不可用）
     */
    private Integer validStatus;

    /**
     * 是否关注过 1 关注过
     */
    private Integer subscribe;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 用户级别
     */
    private Integer level;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 服务次数
     */
    private Integer orderNum;

    /**
     * 用户渠道
     */
    private Integer source;

    /**
     * 创建时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime createDate;

    /**
     * 关注时间
     */
    private LocalDateTime subscribeDate;

    /**
     * 更新时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime updateTime;

    /**
     * 第三方合作Id,支付宝1,微信2
     */
    private Integer thirdPartnerId;

    /**
     * 渠道
     */
    private String userFrom;

    /**
     * 马甲名
     */
    private String appCaller;

    /**
     * 会员积分
     */
    private Integer memberBonus;


}
