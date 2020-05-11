package com.company_name.monday.UploadAPI.Model;

import com.google.gson.annotations.SerializedName;

public class FormaResponse{

	@SerializedName("tryon_url")
	private String tryonUrl;

	@SerializedName("status")
	private String status;

	public String getTryonUrl(){
		return tryonUrl;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"FormaResponse{" +
			",tryon_url = '" + tryonUrl + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}