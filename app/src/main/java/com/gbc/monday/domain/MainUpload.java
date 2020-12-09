package com.gbc.monday.domain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.gbc.monday.UploadAPI.Model.FormaResponse;
import com.gbc.monday.UploadAPI.Model.RawBody;
import com.gbc.monday.UploadAPI.Service.FormaUploadInterface;
import com.gbc.monday.activity.UploadToFormaActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainUpload {

    public final static String FORMA_STRING_TAG = "forma_pic_location";
    public final static String TAG = "MainUpload";
    private String uploadedImageUrl;
    private static final String accessToken = "22ea91760c15f69d70c7782fff42cc35eef0cedf";
    private String picturePath;
    RawBody rawBody;
    public final String api_key = "wFb6cHYbNgEDByipeYYQqR75mmZ4QmwcIVCqNHgDwDhHBcuLzqybphkZLWawh3yznEApnURp";
    public final String item_uuid = "2baf6608-3c74-49ba-9c6f-736e74e1547b";


    public MainUpload(String picturePath, Activity context) {
        this.picturePath = picturePath;
        start(context);
    }

    private void start(Activity context) {
        (new UploadToImgurTask(context)).execute(picturePath);
    }

    // Here is the upload task
    class UploadToImgurTask extends AsyncTask<String, Void, Boolean> {

        Activity context;
        private ProgressDialog progressDialog;
        ImageView blackBackground;


        UploadToImgurTask(Activity context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Loading...");
            progressDialog.setMessage("Please wait!");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

        }

        @Override
        protected Boolean doInBackground(String... params) {
            final String upload_to = "https://api.imgur.com/3/upload";
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(upload_to);

            try {
                HttpEntity entity = MultipartEntityBuilder.create()
                        .addPart("image", new FileBody(new File(params[0])))
                        .build();

                httpPost.setHeader("Authorization", "Bearer " + accessToken);
                httpPost.setEntity(entity);

                final HttpResponse response = httpClient.execute(httpPost,
                        localContext);

                final String response_string = EntityUtils.toString(response
                        .getEntity());

                final JSONObject json = new JSONObject(response_string);

                Log.d("tag", json.toString());

                JSONObject data = json.optJSONObject("data");
                uploadedImageUrl = data.optString("link");
                Log.d("tag", "uploaded image url : " + uploadedImageUrl);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) { // after sucessful uploading, show the image in web browser
                new File(picturePath).delete();
                rawBody = new RawBody(api_key, uploadedImageUrl, item_uuid);
                serverCall(new UploadToFormaActivity.DownloadLinkCallback() {
                    @Override
                    public void onDone(String downloadLink) {
                        if (downloadLink.indexOf("://") > 0) {
                            progressDialog.dismiss();
                            (new DownloadFile(context, new UploadToFormaActivity.FinishedDownloadingForma() {
                                @Override
                                public void onDone() {
                                    File imgFile = new File(context.getExternalFilesDir(null).toString() + File.separator + "img.jpg");
                                    if(imgFile.exists()){
                                        Intent intent = new Intent(context, UploadToFormaActivity.class);
                                        intent.putExtra(FORMA_STRING_TAG, imgFile.getPath());
                                        context.startActivity(intent);
                                    }
                                }
                            })).execute(downloadLink);
                        }
                    }
                });
            }
        }

        public void serverCall(final UploadToFormaActivity.DownloadLinkCallback callback){
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://staging.isabq.com/api/open/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            FormaUploadInterface client = retrofit.create(FormaUploadInterface.class);   //using an Interface to make API call
            final Call<FormaResponse> serverCall = client.uploadImage(rawBody);

            serverCall.enqueue(new Callback<FormaResponse>() {
                @Override
                public void onResponse(Call<FormaResponse> call, Response<FormaResponse> response) {
                    FormaResponse formaResponse = response.body();
                    if (formaResponse.getStatus().equals("success")){
                        callback.onDone(formaResponse.getTryonUrl());
                    } else {
                        Toast.makeText(context,"Invalid api-key", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<FormaResponse> call, Throwable t) {
                    Log.i(TAG, "Failed to call Forma");
                    serverCall(callback);
                }
            });

        }


    }

}
