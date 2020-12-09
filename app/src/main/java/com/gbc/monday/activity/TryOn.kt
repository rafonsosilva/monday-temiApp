
package com.gbc.monday.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.View
import com.gbc.monday.R
import com.gbc.monday.domain.MainUpload
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import kotlinx.android.synthetic.main.try_on.*
import java.io.File
import java.io.FileOutputStream

const val CAMERA_ALBUM_NAME = "FormaTryOn"
// temi takes a picture
class TryOn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.try_on)
        camera.setLifecycleOwner(this)
        camera.toggleFacing()
        addTakePictureListener()
        Robot.getInstance().speak(TtsRequest.create("Please take two steps back and try to center yourself with the outlined shirt.", false))
        Robot.getInstance().tiltAngle(20)
        Handler().postDelayed({
            Robot.getInstance().speak(TtsRequest.create("Taking a picture in 3, 2, 1", false))
            Handler().postDelayed({
                camera.takePicture()
            }, 3100)
        }, 5000)
    }

    private fun addTakePictureListener() {
        camera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
                MainUpload(savePhotoAndReturnPath(result.data), this@TryOn)
                camera.visibility = View.INVISIBLE
                ivWhiteShirt.visibility = View.INVISIBLE
            }
        })
    }
// saves the picture
    private fun savePhotoAndReturnPath(byteArray: ByteArray):String {
        val photoDirectory = getPublicStorageDir()
        val photoName = "image.jpg"
        val photoFile = File(photoDirectory, photoName)
        val outputStream = FileOutputStream(photoFile.path)
        outputStream.write(byteArray)
        outputStream.close()
        return photoFile.path
    }

    private fun getPublicStorageDir(): File {
        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), CAMERA_ALBUM_NAME)
        if (!directory.mkdirs()) { Log.d("MainActivity", "Camera directory not created") }
        return directory
    }
}
