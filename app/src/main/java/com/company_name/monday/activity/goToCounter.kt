/**
 * Created by Joe Taveras.
 */

package com.company_name.monday.activity

import com.company_name.monday.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.Context
import android.util.Log
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener


class goToCounter: AppCompatActivity(), OnGoToLocationStatusChangedListener {


	companion object {
		
		fun newIntent(context: Context): Intent {
		
			// Fill the created intent with the data you want to be passed to this Activity when it's opened.
			return Intent(context, goToCounter::class.java)
		}
	}

	override fun onGoToLocationStatusChanged(p0: String?, p1: String?, p2: Int, p3: String?) {
		Log.d("OnComplete", "Ran")
		when(p1) {
			"complete" -> enjoy()
			"abort" -> enjoy()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {

		Robot.getInstance().addOnGoToLocationStatusChangedListener(this)
		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.go_to_counter)
		this.init()
		this.hideTopBarGoToCounter()
	}

	private fun init() {

	}
	fun hideTopBarGoToCounter(){
		Robot.getInstance().hideTopBar()
	}

	fun enjoy() {
		val intent = Intent(this, enjoy::class.java).apply {
			//Robot.getInstance().beWithMe()
			Robot.getInstance().speak(TtsRequest.create("We hope you enjoy your new gear. Let us know if there's anything we can do to help.", false))
		}
		Robot.getInstance().removeOnGoToLocationStatusChangedListener(this)
		startActivity(intent)
	}
}
