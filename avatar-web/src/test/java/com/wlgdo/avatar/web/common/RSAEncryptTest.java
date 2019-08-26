package com.wlgdo.avatar.web.common;

import org.junit.Test;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/5 16:35
 */
public class RSAEncryptTest {

    @Test
    public void encrypt() {
        String message = "ItryMYbesttoLoveU";
        try {
            String cryptoStr = RSAEncrypt.encrypt(message, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDThbPs5yxP4sBtV/eSu+YweW18Optlg5YoAn5xZGwLAQyQ1QCvloDdHVSr3ijkF/+vAPu1MhkMwxPd8G0vGRh7wPQq0B3iyslltWJrlWrtwtqy5Fh5FqFNkOME7OoF6S2DQ8vCbiVwevVBQazQ0odag5Xvd9+3O5oOzeuNX1bvPwIDAQAB");
            System.out.println(cryptoStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decrypt() {
        String cyptoStr = "y2K/yDKZs5s3rZX1ZfKf8+LmTPuzlvCoGltnfdpGF8yCAmFfLv0+0ccbzhXjUB8YG2zGL31Q6ys6Xm/8LDd8UupAXETlhC726CmHgKEodB/LxN1OfTqOUFBI0kk6zCPuAmifPbIAu1A4WkHI46owJq2h9TMjHhYm9jGmATlRtgk=";

        try {
            String deCrypt = RSAEncrypt.decrypt(cyptoStr, "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANOFs+znLE/iwG1X95K75jB5bXw6m2WDligCfnFkbAsBDJDVAK+WgN0dVKveKOQX/68A+7UyGQzDE93wbS8ZGHvA9CrQHeLKyWW1YmuVau3C2rLkWHkWoU2Q4wTs6gXpLYNDy8JuJXB69UFBrNDSh1qDle9337c7mg7N641fVu8/AgMBAAECgYB1fqt3SOJAbcBd/KM1CtLO0mSSwStYtENQbjI2YoXxht+oA+mhn4RtTsGdxoYITZxlZbJr8Cwh/qqmecrsgpAqCyFGsFoZzYgGa5LmLoVq0lLYnbpD86zMVKVPrUkvDL49HHkkjRD104YKOWtxe0FRGBkwgBqKetZDJA0DH+mY4QJBAPUjN/alcm3T0lsKRgpHjOGRoQ+RaBq4lHA5Q7UYCUIbyHnTqgD4T+Zf0U/MYYFRhiy9YiCppx4yzolXnIL3KAsCQQDc5SkSAPX6NuQV12rCKTEQG61hF4C524CpuRxdeog9/8xvS1I+YzLVI+WyGNc77Nxm+cvOOxRv3yZt7RY1l/IdAkEAoe5t3YRVHq+6WWFj+w5gxfEJT9thxaUAiVGKpGoIU58+wxtLRfDB9xB8mBYOovpTg+Jmm+T1/EDbpmY1gV37GQJAD21Ntf0tMKFewou93/uCeq6EKFC8474JuVC9Q2YIV9Qike8/ui2xYiNUqmCDv6KmLebqLegAYGPESk8RiwKmnQJBAJvkafHLfhft7gmGB9MWjZ3W8I1w8ApZE1pcnHBdRqsd/crzruiZAb1rGLiz5nZdTHIOph49D+VruAZC+U+tR0o=");
            System.out.println(deCrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}