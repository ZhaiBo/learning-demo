package ink.zhaibo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SMSConfig {
    @Value("${aliyun.sms.signName}")
    private String signName;

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;

    @Value("${aliyun.sms.productName}")
    private String productName;

    @Value("${aliyun.sms.domain}")
    private String domain;

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.region}")
    private String region;

    @Value("${aliyun.sms.endpoint}")
    private String endpoint;

    public String getSignName() {
        return signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getDomain() {
        return domain;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getRegion() {
        return region;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
