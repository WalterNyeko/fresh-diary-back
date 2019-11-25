package com.fresh.freshdiary.aws;

import java.io.File;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Service
public class AWSConfigurations {


	public AWSConfigurations() {}
	
	public AWSCredentials verifyCredentials(String accessKeyId, String secretKey) {
		
		AWSCredentials credentials = new BasicAWSCredentials(
				accessKeyId, 
				secretKey
				);
		return credentials;
	}
	
	public AmazonS3Client getS3Client(String accessKeyId, String secretKey) {
		AmazonS3Client s3client = (AmazonS3Client) AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(this.verifyCredentials(accessKeyId,secretKey)))
				  .withRegion(Regions.US_EAST_1)
				  .build();
		return s3client;
	}
	
	public String uploadToBucket(String bucketName, String bucketItemURL, String filePath, String accessKeyId, String secretKey) {
		try {
			this.getS3Client(accessKeyId, secretKey).putObject(
					  bucketName, 
					  bucketItemURL, 
					  new File(filePath));
			String urlToObject = this.getS3Client(accessKeyId, secretKey).getResourceUrl(bucketName, bucketItemURL);
			return urlToObject;
		}catch(Exception e) {
			return "was unable to upload to bucket "+e.getMessage();
		}
	}

}
