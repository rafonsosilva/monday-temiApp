/**
 * Created by Joe Taveras.
 */

package com.gbc.monday.activity

import com.gbc.monday.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.Context
import android.os.CountDownTimer
import com.robotemi.sdk.Robot


class enjoy: AppCompatActivity() {

	companion object {
		
		fun newIntent(context: Context): Intent {
		
			// Fill the created intent with the data you want to be passed to this Activity when it's opened.
			return Intent(context, enjoy::class.java)
		}
	}
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
	
		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.enjoy)
		this.init()
		val minute:Long = 1000 * 5 // 1000 milliseconds = 1 second

		// 1 minute
		val millisInFuture:Long = (minute)

		// Count down interval 1 second
		val countDownInterval:Long = 1000
		this.restartTimer(millisInFuture,countDownInterval).start()
	}

	private fun init() {

	}

	private fun restartTimer(millisInFuture:Long,countDownInterval:Long): CountDownTimer {
		return object: CountDownTimer(millisInFuture,countDownInterval){
			override fun onTick(millisUntilFinished: Long){}

			override fun onFinish() {

				restart()
				cancel()


			}
		}

	}

	fun restart() {
		val intent = Intent(this, menu::class.java).apply {
			Robot.getInstance().goTo("entrance")


		}

		startActivity(intent)
	}
}
