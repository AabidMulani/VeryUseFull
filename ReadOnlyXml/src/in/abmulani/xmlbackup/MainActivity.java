package in.abmulani.xmlbackup;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	public static ArrayList<String> idList = new ArrayList<String>();
	public static ArrayList<String> nameList = new ArrayList<String>();
	public static ArrayList<String> nearbyList = new ArrayList<String>();
	public static ArrayList<String> timeList = new ArrayList<String>();
	public static ArrayList<String> latitudeList = new ArrayList<String>();
	public static ArrayList<String> longitudeList = new ArrayList<String>();
	CreatBackUpFile writexml;
	String path = Environment.getExternalStorageDirectory() + "/GPS_APP/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_layout);
		Button writebtn = (Button) findViewById(R.id.button1);
		Button readbtn = (Button) findViewById(R.id.button2);
		InitializeListData();
		
		writebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				writexml = new CreatBackUpFile(path,"BackUp.xsm");
			}
		});
		
		readbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new ReadBackUpFile(MainActivity.this);
			}
		});
	}

	private void InitializeListData() {
		idList.add("1");
		nameList.add("Name1");
		nearbyList.add("NearBy1");
		timeList.add("Time1");
		latitudeList.add("Latitude1");
		longitudeList.add("Latitude1");

		idList.add("2");
		nameList.add("Name2");
		nearbyList.add("NearBy2");
		timeList.add("Time2");
		latitudeList.add("Latitude2");
		longitudeList.add("Latitude2");

		idList.add("3");
		nameList.add("Name3");
		nearbyList.add("NearBy3");
		timeList.add("Time3");
		latitudeList.add("Latitude3");
		longitudeList.add("Latitude3");

		idList.add("4");
		nameList.add("Name4");
		nearbyList.add("NearBy4");
		timeList.add("Time4");
		latitudeList.add("Latitude4");
		longitudeList.add("Latitude4");

		idList.add("5");
		nameList.add("Name5");
		nearbyList.add("NearBy5");
		timeList.add("Time5");
		latitudeList.add("Latitude5");
		longitudeList.add("Latitude5");

	}
}
