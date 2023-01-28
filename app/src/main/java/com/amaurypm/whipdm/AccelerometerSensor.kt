package com.amaurypm.whipdm

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

/**
 * Creado por Amaury Perea Matsumura el 28/01/23
 */
class AccelerometerSensor(
    context: Context
): AndroidSensor(    //Hereda de AndroidSensor
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorType = Sensor.TYPE_ACCELEROMETER
)

class LightSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_LIGHT,
    sensorType = Sensor.TYPE_LIGHT
)

class ProximitySensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_PROXIMITY,
    sensorType = Sensor.TYPE_PROXIMITY
)