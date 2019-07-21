package com.wlgdo.avatar.common.utils;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/7/21 22:58
 */
public class TelephoneUtil {
    public static boolean FormatTransfer = true;// 是否将电话号码统一格式化为xxx-xxxx-xxx
    public static boolean MosaicSwtich = true;// 是否将电话号码统一格式化为xxx-xxxx-xxx
    public static final String MosaicMask = "*****************";// 声明常量掩码，最大支持18位
    public static final String[] TelephoneSeparator = new String[]{"-", "\\(", "\\)", "（", "）", "转", "·"};// 几个常见的座机号码常见分隔符

    /**
     * 添加掩码
     *
     * @param telePhoneNum
     * @return String
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年8月22日下午6:39:03
     */
    public static String mosaic(String telePhoneNum) {
        return isAlpha(telePhoneNum, TelephoneType.Mobile) ? mosaic(telePhoneNum, TelephoneType.Mobile) :
                mosaic(telePhoneNum, TelephoneType.Telephone);
    }

    public static String mosaic(String telePhoneNum, TelephoneType telephoneType) {
        return telephoneType.mosaic(telePhoneNum);
    }

    /**
     * 格式化电话号码
     *
     * @param telePhoneNum
     * @return String
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年8月22日下午6:39:14
     */
    public static String format(String telePhoneNum) {
        return isAlpha(telePhoneNum, TelephoneType.Mobile) ? TelephoneType.Mobile.format(telePhoneNum) :
                TelephoneType.Telephone.format(telePhoneNum);
    }

    public static String format(String telePhoneNum, TelephoneType telephoneType) {
        return telephoneType.format(telePhoneNum);
    }

    /**
     * 判断是否电话号码
     *
     * @param telePhoneNum
     * @return boolean
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年8月22日下午6:39:32
     */
    public static boolean isAlpha(String telePhoneNum) {
        return !isAlpha(telePhoneNum, TelephoneType.Mobile) && !isAlpha(telePhoneNum, TelephoneType.Telephone);
    }

    public static boolean isAlpha(String telePhoneNum, TelephoneType telephoneType) {
        return null != telephoneType.format(telePhoneNum);
    }

    /**
     * 加密手机号码
     *
     * @param telphoneNum
     * @return String
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年8月22日下午6:39:49
     */
    public static String encrypt(String telphoneNum) {
        if (!isAlpha(telphoneNum)) {
            System.out.println("不是通常的电话号码");
            return null;
        }
        String resource = Thread.currentThread().getStackTrace()[2].getClassName() + "."
                + Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("调用加密得资源是：" + resource);
        //TODO 加密算法
        return "自定义加密算法";
    }

    /**
     * 解密手机号码
     *
     * @param telphoneNum
     * @return String
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年8月22日下午6:39:58
     */
    public static String decrypt(String telphoneNum) {
        if (!isAlpha(telphoneNum)) {
            System.out.println("不是通常的电话号码");
            return null;
        }
        String resource = Thread.currentThread().getStackTrace()[2].getClassName() + "."
                + Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("调用解密得资源是：" + resource);
        return "自定义解密算法";
    }

    /**
     * 电话类型枚举:手机号和座机号
     *
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年8月22日 下午6:02:27
     */
    enum TelephoneType {
        Mobile("Mobile", "") {
            @Override
            protected String mosaic(String telePhoneNum) {
                if (!isAlpha(telePhoneNum)) {
                    return null;
                }
                return MosaicSwtich ? telePhoneNum.substring(0, 3) + "****" + telePhoneNum.substring(7, 11) :
                        telePhoneNum;
            }

            @Override
            protected String format(String telePhoneNum) {
                if (!telePhoneNum.matches("^[\\d]{11}$") || !telePhoneNum.startsWith("1")) {
                    System.out.println("不是通常手机号码");
                    return null;
                }
                return telePhoneNum;
            }

            @Override
            protected boolean isAlpha(String telePhoneNum) {
                return null != format(telePhoneNum);
            }
        },
        /**
         * 座机号码
         */
        Telephone("Telephone", "") {
            @Override
            protected String mosaic(String telePhoneNum) {
                if (!isAlpha(telePhoneNum)) {
                    return null;
                }
                if (telePhoneNum.replaceAll("[^0-9]", "-").length() == 7) {
                    return telePhoneNum.substring(0, 2) + "***" + telePhoneNum.substring(7);
                }
                String mosaicStr = MosaicMask.substring(0, telePhoneNum.length() - 7);
                String telephoneTemp = telePhoneNum.replaceAll("[^0-9]", "");
                return MosaicSwtich ? telephoneTemp.substring(0, 3) + mosaicStr +
                        telephoneTemp.substring(telePhoneNum.length() - 4) : telePhoneNum;
            }

            @Override
            protected String format(String telePhoneNum) {
                if (null == telePhoneNum || "".equals(telePhoneNum.replaceAll(" ", ""))) {
                    System.out.println("不是通常的电话号码");
                    return null;
                }
                String telNoTemp = matchTelephoneNum(telePhoneNum);
                if (!telNoTemp.matches("^[0-9]{7,16}$")) {
                    System.out.println("不是通常的电话号码:" + telePhoneNum);
                    return null;
                }
                if (FormatTransfer) {
                    telePhoneNum = telePhoneNum.replaceAll("[^0-9]", "-").startsWith("-") ?
                            telePhoneNum.replaceAll("[^0-9]", "-").substring(1) :
                            telePhoneNum.replaceAll("[^0-9]", "-");
                }
                return telePhoneNum;
            }

            @Override
            protected boolean isAlpha(String telePhoneNum) {
                return null != format(telePhoneNum);
            }
        };

        private String telPhoneNum;
        private String telPhoneType;

        TelephoneType(String telPhoneNum, String telPhoneType) {
            this.telPhoneNum = telPhoneNum;
            this.telPhoneType = telPhoneType;
        }

        protected String matchTelephoneNum(String telePhoneNum) {
            for (String separator : TelephoneSeparator) {
                telePhoneNum = telePhoneNum.replaceAll(separator, "");
            }
            return telePhoneNum;
        }

        protected abstract String mosaic(String telePhoneNum);

        protected abstract String format(String telePhoneNum);

        protected abstract boolean isAlpha(String telePhoneNum);

        public String getTelPhoneNum() {
            return telPhoneNum;
        }

        public void setTelPhoneNum(String telPhoneNum) {
            this.telPhoneNum = telPhoneNum;
        }

        public String getTelPhoneType() {
            return telPhoneType;
        }

        public void setTelPhoneType(String telPhoneType) {
            this.telPhoneType = telPhoneType;
        }
    }

}
