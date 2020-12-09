/**
 * Created by Joe Taveras.
 */

package com.gbc.monday.activity

import android.content.Intent
import android.os.Bundle
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.gbc.monday.R
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest


class afterAddToBag: AppCompatActivity() {

	companion object {
		
		fun newIntent(context: Context): Intent {
		
			// Fill the created intent with the data you want to be passed to this Activity when it's opened.
			return Intent(context, afterAddToBag::class.java)
		}
	}
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
	
		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.after_add_to_bag)
		this.init()
		this.hideTopBarAfterAddToBag()
	}

	private fun init() {

	}

	fun personalAssistantAfterAddToBag(view: View) {
		Robot.getInstance().startTelepresence(Robot.getInstance().adminInfo?.name, Robot.getInstance().adminInfo?.userId)
	}

	fun pickUp(view: View) {
		val intent = Intent(this, goToCounter::class.java).apply {

			Robot.getInstance().goTo("checkout")
			Robot.getInstance().speak(TtsRequest.create("Follow me to the checkout counter to pick up your new clothes.", false))


		}


		startActivity(intent)
	}

	fun hideTopBarAfterAddToBag(){
		Robot.getInstance().hideTopBar()
	}
}
