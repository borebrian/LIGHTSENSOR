package com.message.lightsebsor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(),SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    var sensor:Sensor? =null
    var sensorManager : SensorManager?=null
    var isRunning =false


    override fun onSensorChanged(event: SensorEvent?) {

       try {
           if(event!!.values[0]<30 && isRunning==false)
           {
               isRunning==true
               display_view.visibility=View.VISIBLE
           }
           else{
               isRunning==false
               display_view.visibility=View.INVISIBLE
           }
       }

    catch (e : IOException){

    }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display_view.visibility = View.INVISIBLE

        sensorManager =getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)


        }
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}
