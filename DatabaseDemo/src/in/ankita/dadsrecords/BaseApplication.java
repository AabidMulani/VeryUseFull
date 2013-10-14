package in.ankita.dadsrecords;

import java.util.ArrayList;

import android.app.Application;

public class BaseApplication extends Application {

	static ArrayList<String> IDs=new ArrayList<String>();
	static ArrayList<String> TimeStamps=new ArrayList<String>();
	static ArrayList<String> PT_Val1=new ArrayList<String>();
	static ArrayList<String> PT_Val2=new ArrayList<String>();
	static ArrayList<String> PT_INR=new ArrayList<String>();
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	public static void ClearData(){
		IDs.clear();
		TimeStamps.clear();
		PT_INR.clear();
		PT_Val1.clear();
		PT_Val2.clear();
	}
	
}
