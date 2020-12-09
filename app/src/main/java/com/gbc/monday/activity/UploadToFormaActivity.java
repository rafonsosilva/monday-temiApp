package com.gbc.monday.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gbc.monday.R;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

import java.io.File;

import static com.gbc.monday.domain.MainUpload.FORMA_STRING_TAG;

public class UploadToFormaActivity extends AppCompatActivity {

    public interface DownloadLinkCallback {
        void onDone(String downloadLink);
    }

    public interface FinishedDownloadingForma {
        void onDone();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_to_forma);
        String imageFilePath = getIntent().getStringExtra(FORMA_STRING_TAG);
        File formaImageFile = new File(imageFilePath);
        Bitmap myBitmap = BitmapFactory.decodeFile(formaImageFile.getAbsolutePath());
        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setImageBitmap(myBitmap);
        myImage.setVisibility(View.VISIBLE);
        Robot.getInstance().speak(TtsRequest.create("Nice shot", false));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(UploadToFormaActivity.this, afterAddToBag.class));
            }
        }, 10000);
    }
}

