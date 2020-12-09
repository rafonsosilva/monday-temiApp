/**
 * Created by Joe Taveras.
 * Improved by Nir Frenkel
 */
// This is the main menu of this app
package com.gbc.monday.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.gbc.monday.R
import com.gbc.monday.descrp
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest


class menu: AppCompatActivity() {
// request Android permissions
	private val REQUEST_EXTERNAL_STORAGE = 1
	private val PERMISSIONS_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

	companion object {

		fun newIntent(context: Context): Intent {

			// Fill the created intent with the data you want to be passed to this Activity when it's opened.
			return Intent(context, menu::class.java)
		}
	}


	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.menu)
		this.init()
		verifyStoragePermissions(this)
		this.hideTopBarMenu()
		// hide top bar with delay
		Handler().postDelayed({
			Robot.getInstance().hideTopBar()
		}, 100)
	}

	private fun init() {

	}
// explore button takes to the explore page and activity
	fun explore(view: View) {
		val intent = Intent(this, explore::class.java).apply {
			Robot.getInstance().speak(TtsRequest.create("What can I help you find?", false)) // temi will say this, false = no writing on the screen, true = writing on screen

		}

		startActivity(intent) // starts the new activity (new page)
	}

	fun shop(view: View) {
		val intent = Intent(this, shop::class.java).apply {
			Robot.getInstance().speak(TtsRequest.create("Let's get to shopping. To add an item to your bag or to try it on, simply scan the item's barcode to my camera!", false))

		}

		startActivity(intent)
	}

	fun personalAssistantMenu(view: View) {
		Robot.getInstance().startTelepresence(Robot.getInstance().adminInfo?.name, Robot.getInstance().adminInfo?.userId)


	}

	fun hideTopBarMenu() {
		Robot.getInstance().hideTopBar()
	}

	fun verifyStoragePermissions(activity: Activity) {
		// Check if we have write permission
		val permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
		if (permission != PackageManager.PERMISSION_GRANTED) {
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE)
		}
	}

	fun onClickAbout(view: View) {
		val intent = Intent(this, descrp::class.java).apply {  }
		startActivity(intent)
	}

}
