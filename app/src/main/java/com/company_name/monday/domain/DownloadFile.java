package com.company_name.monday.domain;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.company_name.monday.activity.UploadToFormaActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFile extends AsyncTask<String, Integer, String> {

    private ProgressDialog progressDialog;
    @SuppressLint("StaticFieldLeak")
    private Context activity;
    private UploadToFormaActivity.FinishedDownloadingForma listener;


    DownloadFile(Context activity, UploadToFormaActivity.FinishedDownloadingForma listener) {
        this.activity = activity;
        this.listener = listener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        //private MainActivity.DownloadListener listener;
        String imageName = "Forma Image";
        progressDialog.setTitle(imageName);
        progressDialog.setMessage("Downloading, Please wait!");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        String fileName = "img.jpg";

        try {
            URL url = new URL(strings[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            int fileLength = connection.getContentLength();

            InputStream input = new BufferedInputStream(url.openStream());

            OutputStream output = new FileOutputStream(new File(activity.getExternalFilesDir(null) + File.separator + fileName));

            byte[] data = new byte[1024];
            long total = 0;
            int count;

            //Check for network connection loss, otherwise it would stop in the middle "Cannot parse this package"

            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    protected void onPostExecute(String fileName) {
        super.onPostExecute(fileName);
        progressDialog.dismiss();
        listener.onDone();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }
}
