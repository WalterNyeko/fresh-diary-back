package com.fresh.freshdiary.aws;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AWSConfigurations {

	public AWSConfigurations() {}
	
	public AWSCredentials verifyCredentials() {
		AWSCredentials credentials = new BasicAWSCredentials(
				  "AKIAJ4MMMPBMCTHLVXPA", 
				  "RH+i+Gm1FHTcKZU1+wd6DuB46ZKr+F96NItVXln7"
				);
		return credentials;
	}
	
	public AmazonS3 getS3Client() {
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(this.verifyCredentials()))
				  .withRegion(Regions.US_EAST_2)
				  .build();
		return s3client;
	}
	
	public void createS3Bucket(String bucketName) {
		if(this.getS3Client().doesBucketExist(bucketName)) {
		    System.out.println("Bucket does not exits");
		    return;
		}
		 
		this.getS3Client().createBucket(bucketName);
	}
	
	public List<Bucket> listBuckets(){
		List<Bucket> buckets = this.getS3Client().listBuckets();
		return buckets;
	}
	
	public void deleteS3Bucket(String bucketName) {
		try {
		    this.getS3Client().deleteBucket(bucketName);
		} catch (AmazonServiceException e) {
		    System.err.println(""+e.getErrorMessage());
		    return;
		}
	}
	
	public boolean uploadToBucket(String bucketName, String bucketItemURL, String filePath) {
		try {
			this.getS3Client().putObject(
					  bucketName, 
					  bucketItemURL, 
					  new File(filePath)
					);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public List<String> listBucketItems(String bucketName) {
		List<String> items = new ArrayList<>();
		ObjectListing objectListing = this.getS3Client().listObjects(bucketName);
		for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
			items.add(os.getKey());
		}
		return items;
	}
	
	public S3Object getItemFromBucket(String bucketName, String itemName) {
		S3Object s3Object = this.getS3Client().getObject(bucketName, itemName);
		return s3Object;
	}
	
	public void renameItemInBucket(String bucketName, String itemName, String newBucket, String newItemName) {
		this.getS3Client().copyObject(
				bucketName, 
				itemName, 
				newBucket, 
				newItemName
				);
	}
	
	public void deleteItemFromBucket(String bucketName, String itemName) {
		this.getS3Client().deleteObject(bucketName, itemName);
	}

}
