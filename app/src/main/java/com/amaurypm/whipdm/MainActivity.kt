package com.amaurypm.whipdm

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amaurypm.whipdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var sensorManager: SensorManager? = null
    var accelerometer: Sensor? = null

    var sensorEventListener: SensorEventListener? = null
    var mediaPlayer: MediaPlayer? = null

    var accelerometerSensor: AccelerometerSensor? = null

    var whip = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        //accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        mediaPlayer = MediaPlayer.create(this, R.raw.whip)

        accelerometerSensor = AccelerometerSensor(this)
                
        accelerometerSensor?.setOnSensorValuesChangedListener{ values ->
            var accelValue = values[0]
            if(accelValue!! < -5 && whip == 0){ //Se movió hacia la izquierda
                whip++
            }else if(accelValue > 5 && whip ==1){
                whip++
            }
            if(whip==2){
                whip = 0
                mediaPlayer?.start()
                blinkScreen()
            }
        }


        /*if(accelerometer != null){
            //Sí hay acelerómetro
            sensorEventListener = object: SensorEventListener{
                override fun onSensorChanged(event: SensorEvent?) {
                    //Cuando el sensor cambia
                    var accelValue = event?.values?.get(0)
                    Log.d("LOGS", "Valor del acelerómetro: $accelValue")

                    if(accelValue!! < -5 && whip == 0){ //Se movió hacia la izquierda
                        whip++
                    }else if(accelValue > 5 && whip ==1){
                        whip++
                    }
                    if(whip==2){
                        whip = 0
                        mediaPlayer?.start()
                        blinkScreen()
                    }

                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

                }
            }
        }else{
            //Manejo del error con el acelerómetro
            Toast.makeText(this, "Dispositivo no compatible", Toast.LENGTH_SHORT).show()
            finish()
        }*/
    }

    override fun onResume() {
        super.onResume()
        /*sensorManager?.registerListener(
            sensorEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )*/
        accelerometerSensor?.startListening()

    }

    override fun onPause() {
        super.onPause()
        //sensorManager?.unregisterListener(sensorEventListener)
        accelerometerSensor?.stopListening()
    }

    fun blinkScreen() {

        val anim = ObjectAnimator.ofInt(binding.clMain.background, "alpha", 0, 255).apply {
            duration = 500L
            repeatCount = 0
            setEvaluator(ArgbEvaluator())
        }

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                binding.clMain.background = getDrawable(R.drawable.gradient_bg)
            }
        })

        anim.start()
    }
}