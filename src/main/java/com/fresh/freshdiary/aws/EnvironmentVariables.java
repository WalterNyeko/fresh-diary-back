package com.fresh.freshdiary.aws;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws-credentials")
public class EnvironmentVariables {

	private String awsAccessKeyId;
	private String awsSecretKey;
	private String bucket;
	private String awsQRCodeBaseLink;
	
	public EnvironmentVariables() {
	}

	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}

	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getAwsQRCodeBaseLink() {
		return awsQRCodeBaseLink;
	}

	public void setAwsQRCodeBaseLink(String awsQRCodeBaseLink) {
		this.awsQRCodeBaseLink = awsQRCodeBaseLink;
	}
	

}
