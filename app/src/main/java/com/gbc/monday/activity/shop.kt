/**
 * Created by Joe Taveras.
 */

package com.gbc.monday.activity

import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import com.gbc.monday.R
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.content.Context
import androidx.core.view.animation.PathInterpolatorCompat
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest


class shop: AppCompatActivity() {

	companion object {
		
		fun newIntent(context: Context): Intent {
		
			// Fill the created intent with the data you want to be passed to this Activity when it's opened.
			return Intent(context, shop::class.java)
		}
	}

	private lateinit var nextImageView: ImageView
	private lateinit var nextCopyImageView: ImageView
	private lateinit var nextCopy2ImageView: ImageView
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
	
		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.shop)
		this.init()
		this.startAnimationOne()
	}

	private fun init() {
		nextImageView = this.findViewById(R.id.next_image_view)

		// Configure next copy component
		nextCopyImageView = this.findViewById(R.id.next_two_image_view)

		// Configure next copy 2 component
		nextCopy2ImageView = this.findViewById(R.id.next_three_image_view)
	}

	fun personalAssistantShop(view: View) {
		Robot.getInstance().startTelepresence(Robot.getInstance().adminInfo?.name, Robot.getInstance().adminInfo?.userId)
	}


	fun screenTap(view: View) {
		val intent = Intent(this, addTryOn::class.java).apply {
			Robot.getInstance().speak(TtsRequest.create("Good find! Would you like to add it to your bag, or try it on?", false))
		}
		startActivity(intent)
	}

	fun startAnimationOne() {
//animation
		val animator1 = ObjectAnimator.ofPropertyValuesHolder(nextCopyImageView, PropertyValuesHolder.ofKeyframe(View.ALPHA, Keyframe.ofFloat(0f, 0f), Keyframe.ofFloat(0.07f, 0f), Keyframe.ofFloat(0.33f, 1f), Keyframe.ofFloat(0.45f, 1f), Keyframe.ofFloat(0.53f, 0f), Keyframe.ofFloat(0.67f, 0f), Keyframe.ofFloat(1f, 1f)))
		animator1.duration = 3000
		animator1.interpolator = PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f)

		val animator2 = ObjectAnimator.ofPropertyValuesHolder(nextCopyImageView, PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y, Keyframe.ofFloat(0f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, this.resources.displayMetrics)), Keyframe.ofFloat(0.4f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics)), Keyframe.ofFloat(0.72f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics)), Keyframe.ofFloat(0.8f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, this.resources.displayMetrics)), Keyframe.ofFloat(1f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics))))
		animator2.duration = 2500
		animator2.interpolator = PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f)

		val animatorSet1 = AnimatorSet()
		animatorSet1.playTogether(animator1, animator2)
		animatorSet1.setTarget(nextCopyImageView)

		val animator3 = ObjectAnimator.ofPropertyValuesHolder(nextImageView, PropertyValuesHolder.ofKeyframe(View.ALPHA, Keyframe.ofFloat(0f, 0f), Keyframe.ofFloat(0.23f, 1f), Keyframe.ofFloat(0.46f, 1f), Keyframe.ofFloat(0.54f, 0f), Keyframe.ofFloat(0.68f, 0f), Keyframe.ofFloat(1f, 1f)))
		animator3.duration = 2800
		animator3.interpolator = PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f)

		val animator4 = ObjectAnimator.ofPropertyValuesHolder(nextImageView, PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y, Keyframe.ofFloat(0f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, this.resources.displayMetrics)), Keyframe.ofFloat(0.36f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics)), Keyframe.ofFloat(0.57f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics)), Keyframe.ofFloat(0.68f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, this.resources.displayMetrics)), Keyframe.ofFloat(1f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics))))
		animator4.duration = 2800
		animator4.interpolator = PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f)

		val animatorSet2 = AnimatorSet()
		animatorSet2.playTogether(animator3, animator4)
		animatorSet2.setTarget(nextImageView)

		val animator5 = ObjectAnimator.ofPropertyValuesHolder(nextCopy2ImageView, PropertyValuesHolder.ofKeyframe(View.ALPHA, Keyframe.ofFloat(0f, 0f), Keyframe.ofFloat(0.13f, 0f), Keyframe.ofFloat(0.33f, 1f), Keyframe.ofFloat(0.43f, 1f), Keyframe.ofFloat(0.53f, 0f), Keyframe.ofFloat(0.73f, 0f), Keyframe.ofFloat(1f, 1f)))
		animator5.duration = 3000
		animator5.interpolator = PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f)

		val animator6 = ObjectAnimator.ofPropertyValuesHolder(nextCopy2ImageView, PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y, Keyframe.ofFloat(0f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, this.resources.displayMetrics)), Keyframe.ofFloat(0.33f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics)), Keyframe.ofFloat(0.53f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics)), Keyframe.ofFloat(0.63f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, this.resources.displayMetrics)), Keyframe.ofFloat(1f, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, this.resources.displayMetrics))))
		animator6.duration = 3000
		animator6.interpolator = PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f)

		val animatorSet3 = AnimatorSet()
		animatorSet3.playTogether(animator5, animator6)
		animatorSet3.setTarget(nextCopy2ImageView)

		val animatorSet4 = AnimatorSet()
		animatorSet4.playTogether(animatorSet1, animatorSet2, animatorSet3)
		animatorSet4.start()
	}
}
