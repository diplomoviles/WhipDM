package com.amaurypm.whipdm

/**
 * Creado por Amaury Perea Matsumura el 28/01/23
 */
abstract class MeasurableSensor(
    //Se pasa el tipo de sensor en el constructor
    protected val sensorType: Int
) {


    //Algunos sensores devuelven más de un valor, pero son Float
    protected var onSensorValuesChanged: ((List<Float>) -> Unit)? = null

    abstract val doesSensorExist: Boolean
    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: (List<Float>) -> Unit){
        onSensorValuesChanged = listener
    }
}