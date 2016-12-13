package com.example.igx.problem1;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {
    private SensorManager mSensorManager;
    private Sensor mAccelator;
    private Sensor mPressure;
    private Sensor mTemper;

    String location_value;
    String sensor_acc;
    String sensor_pre, sensor_tem;
    double ac;

    static public int count = 0;
    float x, y, z;

    float millibars_of_pressure;
    float temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelator = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mTemper = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);


        Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "LOCATION";
                String val;
                text_selectedType.setText(str);
                GpsStatus gpsStatus;


            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "SENSORS";
                text_selectedType.setText(str);
                String value;
                sensor_acc = "속도: " + Double.toString(ac) + "\n" + "X: "+ Float.toString(x)  + " Y: "+ Float.toString(y) + " Z: "+ Float.toString(z);
                sensor_tem = "\n온도: " + Float.toString(temperature);
                sensor_pre = "\n압력: " + Float.toString(millibars_of_pressure);
                value = sensor_acc + sensor_tem + sensor_pre;
                text_selectedData.setText(value);

            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_str = edit_phoneNumber.getText().toString();
                Intent intent = new Intent(MainActivity.this, Telephony.Sms.Intents.class);
                startActivity(intent);

            }
        });
    }
    public final void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    public  final void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
             x = event.values[0];
             y = event.values[1];
             z = event.values[2];
            ac = Math.sqrt(x * x + y *y + z*z);
        }
        else if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            temperature = event.values[0];
        }
        else if(event.sensor.getType() == Sensor.TYPE_PRESSURE){
            millibars_of_pressure = event.values[0];
        }

    }
}
