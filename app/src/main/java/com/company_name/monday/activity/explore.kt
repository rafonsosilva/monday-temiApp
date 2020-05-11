/**
 * Created by Joe Taveras.
 */

package com.company_name.monday.activity

import android.content.Intent
import android.os.Bundle
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.company_name.monday.R
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

	fun goMen(view: View) {
		Robot.getInstance().goTo("mens clothing")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to the Men's clothing.", false))
	}

	fun goWomen(view: View) {
		Robot.getInstance().goTo("womens clothing")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to the Women's clothing.", false))
	}

	fun goShoes(view: View) {
		Robot.getInstance().goTo("shoes")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to the Shoes.", false))
	}

	fun goAccessories(view: View) {
		Robot.getInstance().goTo("accessories")
		Robot.getInstance().speak(TtsRequest.create("Please follow me to the accessories.", false))
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
