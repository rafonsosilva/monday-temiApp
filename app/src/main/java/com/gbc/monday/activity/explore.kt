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


class explore: AppCompatActivity() {

	companion object {
		
		fun newIntent(context: Context): Intent {
		
			// Fill the created intent with the data you want to be passed to this Activity when it's opened.
			return Intent(context, explore::class.java)
		}
	}
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
	
		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.explore)
		this.init()
		this.hideTopBarExplore()
	}

	private fun init() {

	}

	fun goPackingSupplies(view: View) {
		Robot.getInstance().goTo("Packing supplies")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to the Packing supplies.", false))
	}

	fun goStorageDemoSpace(view: View) {
		Robot.getInstance().goTo("Storage demo space")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to the Storage demo space.", false))
	}

	fun goOtherRetail(view: View) {
		Robot.getInstance().goTo("Other retail")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to other retail products.", false))
	}

	fun goWashroom(view: View) {
		Robot.getInstance().goTo("Washroom")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to the washroom.", false))
	}

	fun backFromExplore(view: View) {
		val intent = Intent(this, menu::class.java).apply {
			Robot.getInstance().speak(TtsRequest.create("How can I help you?", false))
		}


		startActivity(intent)
	}

	fun goShop(view: View) {
		val intent = Intent(this, shop::class.java).apply {
			//Robot.getInstance().beWithMe()
			Robot.getInstance().speak(TtsRequest.create("Let's get to shopping. To add an item to your bag or to try it on, simply scan the item's barcode to my camera!", false))
		}


		startActivity(intent)
	}

	fun personalAssistantExplore(view: View) {
		Robot.getInstance().startTelepresence(Robot.getInstance().adminInfo?.name, Robot.getInstance().adminInfo?.userId)
	}



	fun hideTopBarExplore(){
		Robot.getInstance().hideTopBar()
	}
}
