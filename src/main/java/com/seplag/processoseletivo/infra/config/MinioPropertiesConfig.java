package com.seplag.processoseletivo.infra.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioPropertiesConfig {
    private String url;
    private String hostPublico;
    private int portPublico;
    private String pathPublico;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}