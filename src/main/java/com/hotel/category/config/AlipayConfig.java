package com.hotel.category.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 林晓锋
 * @date 2019/10/22
 * modified: 2019/10/22
 * 功能：
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016101300672826";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCenG+W77Hn4m6C5VkmOMNQQPghIVFNfB6ULma7bLh0r4N4ZXOUKjbTrZtPJch2UlzYWloFqnI7gRJ65EXk7cGoiCWL1GcD/VYbyuDIxP1xMTUOCz8LcSo7Mmi1wPs1t6f2vFcUDo8Wh49rc6Tg2E6LDzthGH4Lk5YCfpaHUCj/XrLocyYHHOrdK8s3DvDXj+0R0o6RUVPFKjM3cpiDq89gJZ6it+Qe+5jeTp1bGdXDdJK8XPusZknUPN4xrYrFNiVfCdkEHSD2g2UMmT+JCeek9ClGkXiyiDFbxZUeCqa0WxM3VyVooAtsIbucvB/FDwaZoIpQjFg/P+V6yI0Peir5AgMBAAECggEAarGGqvygKwD5H9oRhxGF6NVbVEXqP/ovQ7SW7Jrs1ak/Ad0Uy2U+LlRI9gvPdjBfPjZA+jC22OoxOTOXBLEepTS4NQBzL47ZvqZRYRyJ8/uK2eCczyCXrOxy3VhsY60gMmxtExPcEuarhjdrgMhgxVBAvEbSew0TSqhGr80vVuAY1EUqRE20YOngaxeLgE6LlvwOVV1ZqisfsNIeoGSRD3FqIrKwkisEV4uR6tgpdyaJZWssgyYN7S11wmR/8G5kRsbN0/NYZHeN2QFJNr+rASasi4AfWT7sPA045UDQIrBlEnqb8ZNz46wRt93XhdAMfbe/6RYB47Qd5cLJaGYVgQKBgQDK2xKl7O60vaUVkKxf3SkbB5s8RJGGnRQevxEz0YPShPTukFmNaN7i/WWfrWxmD5surjNVfX/B2m+upO02VROEO4V1yhr/Q0yxYfgtL+1HP025sQkQoBpCTHN0XG+WTjBjnbgc5IWWNb81sZz96VvBw3VndrzqSuR7LYvllJP0iQKBgQDIKgFcnnab0p4HRdayQ7bbw3RZz8MQkopt2fHAelI1ICmcjcvqILaFuVSS6MBTGHphQy7J6V1Y1WDDqENfqFDTVsZVcrZaxaC8MxMbiNWKBKEW5LH/M68wkAMYcZlcQKaStg9NenwWb66yVJoTNZDGFWM0XBoCLFMXIpSv0tfG8QKBgCrOAWhbz5756YzcY1MzMspOV5ZnDaIvISC/xpx9mGhiv+BbO1pYh7e0Kq6ABMCgiGlYPuLKQZwhQLHEgX2WhDDB8o6BYCL/C37eomS+mT4QgJEvZy9H6WKWCULDgpRf2Vwb2s7V9c5VdwyYdv0pMcB4Qxza9FAoD4BigwOlz3apAoGAIZlzexGahVOYdU4S2P+m3UL07/KBIvIGTKROAWpHDGXmtkHTHez51n6ipLpvNkOgn10AqKIEqlm/EXjYPmFpl+A0ELndxGTyM0U7it6wQae/RrGC83zAhp1V969x99teWrg232GVHMsIfyTWbC0MrUN/0JU5RG4eUH7ciJsS33ECgYArAO0mojsf8lqP5KpvTNGQ9gB332eXT+J+nbTto4F8RKVvjiPxlOdU+P9SysQzDWUIrTrmNWSqBLmKICTVsnucDQYJg0lWZB1+wlbUX4X2Ro/VcdvUTI7dTxPXcV9NFKfNjqfMvHTzUhVeN2d0qhz+4EGAcbTdSgCSTXNU3/Ca+Q==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkCtBw+211V4z+EPNm7uzO9aJQ/okRBl4spVFrX2999AQgDzlQMoOTdkPotbbqV3e/rPMTU0ppPUiVX3WdpvJAwL9jTofEvMwi0pzofJPvwEM9w3g1wmi2WcvzyHbBwyCFjjxZPcKzPU5kRbokr5Qj6NfDn9istc2gy910hfeRFuWWK4SYVfEl9NF9HdEgrxMfkhMojMrYQIcWlHPIw2j+RxfdrpddPjBjjsCnmnuMe7E+5Mq/GPm9nCM7NnEAAvjAH/v3/EvU7TRn9Z44rPTqjSF75SVQfz9VHLEnJ7AQ2GMO5yXP3E0Y0rE8mZ3wsHhfZYnyIHdywoZwoVCgyMMjQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://10.20.5.29:8080/json/orderForm/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://10.20.5.29:8080/json/orderForm/returnUrl";
    public static String return_url2 = "http://10.20.5.29:8080/booking/alipay";
    public static String return_url3 = "http://10.20.5.29:8080/RecreationOrder/alipay";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\Users\\20271\\Desktop\\change.txt";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
