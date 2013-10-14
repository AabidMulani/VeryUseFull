package com.example.tiltmanager;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class TiltActivity extends Activity {
TiltCalc tilt;
SimpleCircle circ;
int MoveSpeed=4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tilt=new TiltCalc(getApplicationContext());
		circ=new SimpleCircle(getApplicationContext());
		setContentView(circ);		
	}
	public void DisplayData(){
		
		float[] valz=tilt.getTilt();
		Log.v("Val 1",valz[1]+"");
		Log.v("Val 2",valz[2]+"");
		
		if(valz[1]>=0){
			circ.setY_val(-MoveSpeed);
		}else{
			circ.setY_val(MoveSpeed);
		}
		
		if(valz[2]>=0){
			circ.setX_val(MoveSpeed);
		}else{
			circ.setX_val(-MoveSpeed);
		}
//		circ.ReDraw();		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tilt, menu);
		return true;
	}

@Override
	protected void onDestroy() {
		tilt.Unregister();
		super.onDestroy();
	}
	public class TiltCalc {
	    Context context;
	    private boolean needsRecalc = false;
	    private float[] tilt_data = {0, 0, 0}, gravity = {0, 0, 0}, magnet = {0, 0, 0},answer={0,0,0};;
	    
	    // Change this to make the sensors respond quicker, or slower:
	    private static final int delay = SensorManager.SENSOR_DELAY_GAME;
	    
	    
	    // Special class used to handle sensor events:
	    private final SensorEventListener listen = new SensorEventListener() {
	        public void onSensorChanged(SensorEvent e) {
	            final float[] vals = e.values, target;
	            target = (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) ? gravity : magnet;
	            needsRecalc = true;
	            System.arraycopy(vals, 0, target, 0, 3);
	            DisplayData();
	        }
	        
	        public void onAccuracyChanged(Sensor event, int res) {}
	    };
	    
	    // The constructor will use a context object to register itself for various inputs:
	    public TiltCalc(Context c) {
	    	this.context=c;
	        SensorManager man = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE); 
	        
	        Sensor mag_sensor = man.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	        Sensor acc_sensor = man.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	        if( man.registerListener(listen, mag_sensor, delay) &&
	                   man.registerListener(listen, acc_sensor, delay) ) {
	            Log.d("TiltCalc", "No gyroscope, falling back on accelerometer+compass.");
	        
	        } else {
	            Log.d("TiltCalc", "No acceptable hardware found.");
	           Unregister();
	        }
	    }
	    
	    public void Unregister(){
	    	SensorManager man = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE); 
	    	 man.unregisterListener(listen);
	    }
	    // Will return the most up-to-date tilt data in the vals[] array
	    public float[] getTilt() {
	        
	        // If some of the data has been changed, then we need to recalculate some things...
	        if(needsRecalc) {
	            float[] R={0,0,0,0,0,0,0,0,0};
	            
	            // Calculate the rotation matrix, and use that to get the orientation:
	            if(SensorManager.getRotationMatrix(R, null, gravity, magnet))
	                SensorManager.getOrientation(R, tilt_data);
	            
	            needsRecalc = false;
	        }
	        
	        System.arraycopy(tilt_data, 0, answer, 0, 3);
	        return answer;
	    }
	}
}
