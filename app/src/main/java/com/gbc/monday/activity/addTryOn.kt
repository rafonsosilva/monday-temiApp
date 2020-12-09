/**
 * Created by Joe Taveras.
 */

package com.gbc.monday.activity

import android.os.Bundle
import com.gbc.monday.R
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.Context
import android.view.View
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest


class addTryOn: AppCompatActivity() {

	companion object {
		
		fun newIntent(context: Context): Intent {
		
			// Fill the created intent with the data you want to be passed to this Activity when it's opened.
			return Intent(context, addTryOn::class.java)
		}
	}
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
	
		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.add_try_on)
		this.init()
		Robot.getInstance().stopMovement()
		this.hideTopBarAddTryOn()

	}

	private fun init() {

	}
	fun tryOn (view: View){
		val intent = Intent(this, TryOn::class.java).apply {
		}
		startActivity(intent)
	}

	fun addToBag(view: View) {
		val intent = Intent(this, afterAddToBag::class.java).apply {
			Robot.getInstance().speak(TtsRequest.create("The White Short Sleeved Shirt has been added to your bag. Let's get back to shopping!", false))

		}

		startActivity(intent)
	}

	fun personalAssistantAddTryOn(view: View) {
		Robot.getInstance().startTelepresence(Robot.getInstance().adminInfo?.name, Robot.getInstance().adminInfo?.userId)
	}

	fun hideTopBarAddTryOn(){
		Robot.getInstance().hideTopBar()
	}
}
