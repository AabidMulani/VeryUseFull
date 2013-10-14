package in.ankita.dadsrecords;

import java.sql.Timestamp;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final int DATE_DIALOG_ID = 1;
	RelativeLayout OutterLayout, AddEntryLayout, ViewReportLayout;
	EditText Pt_val1, Pt_val2, Pt_inr, Pt_date;
	Long todaysDate, DateValue;
	ImageButton DateBtn;
	Button save, AddNew, ViewReport;
	long currentTimestamp, selectedTimestamp;
	int mDay;
	int mMonth;
	int mYear;
	Ab_DB dbHandler = new Ab_DB(this, null);
	String[] values = new String[5];
	ListView AllDataList;
	TextView NoDataStored;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		OutterLayout=(RelativeLayout) findViewById(R.id.layout_outter);
		AddEntryLayout=(RelativeLayout) findViewById(R.id.layout_add_entry);
		ViewReportLayout=(RelativeLayout) findViewById(R.id.layout_view_all);
		AddNew = (Button) findViewById(R.id.btn_addentry);
		ViewReport = (Button) findViewById(R.id.btn_viewreport);
		AllDataList=(ListView) findViewById(R.id.all_list_view);
		NoDataStored=(TextView) findViewById(R.id.no_data_tv);
		
		GetTodaysDate();
		Pt_val1 = (EditText) findViewById(R.id.pt_val1);
		Pt_val2 = (EditText) findViewById(R.id.pt_val2);
		Pt_inr = (EditText) findViewById(R.id.pt_inr);
		Pt_date = (EditText) findViewById(R.id.pt_datetxt);
		DateBtn = (ImageButton) findViewById(R.id.pt_datebtn);
		DateBtn.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
		AddNew = (Button) findViewById(R.id.btn_addentry);
		ViewReport = (Button) findViewById(R.id.btn_viewreport);
		save = (Button) findViewById(R.id.btn_save);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validation()) {
					if(dbHandler.AddThisEntry(values)){
						Toast.makeText(getApplicationContext(), "Done...!", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(getApplicationContext(), "ERROR...", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
		//On Click of View Reports
		ViewReport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Animation slideout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_top_out);
				AddEntryLayout.startAnimation(slideout);
				AddEntryLayout.setVisibility(View.GONE);
				Animation slideIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_bottom);
				ViewReportLayout.startAnimation(slideIn);
				ViewReportLayout.setVisibility(View.VISIBLE);
				BaseApplication.ClearData();
				int length=dbHandler.GetAll();
				if(length==-1){
					Toast.makeText(getApplicationContext(), "ERROR...", Toast.LENGTH_LONG).show();
				}else if(length==0){
					AllDataList.setVisibility(View.GONE);
					NoDataStored.setVisibility(View.VISIBLE);
				}else{
					AllDataList.setVisibility(View.VISIBLE);
					NoDataStored.setVisibility(View.GONE);
					AdapterClass listadapter= new AdapterClass(MainActivity.this);
					AllDataList.setAdapter(listadapter);
				}
			}
		});
		
		//On Click of Add New value
		AddNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Animation slideout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slie_out_bottom);
				AddEntryLayout.startAnimation(slideout);
				ViewReportLayout.setVisibility(View.GONE);
				
				Animation slidein = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slid_top_in);
				AddEntryLayout.startAnimation(slidein);
				AddEntryLayout.setVisibility(View.VISIBLE);		
				
						
			}
		});
		
		
	}

	protected boolean validation() {
		if (Pt_val1.getText().toString().trim().length() == 0) {
			Pt_val1.setError("Please Enter Some Valid Data");
			Pt_val1.requestFocus();
			return false;
		} else if (Pt_val2.getText().toString().trim().length() == 0) {
			Pt_val2.setError("Please Enter Some Valid Data");
			Pt_val2.requestFocus();
			return false;
		} else if (Pt_date.getText().toString().trim().length() == 0) {
			Toast.makeText(getApplicationContext(), "Please Enter Date",
					Toast.LENGTH_LONG).show();
			DateBtn.performClick();
			return false;
		} else if (Pt_inr.getText().toString().trim().length() == 0) {
			Pt_inr.setError("Please Enter INR value");
			Pt_inr.requestFocus();
			return false;
		} else {
			selectedTimestamp = GetTimeStamp(Pt_date.getText().toString()
					+ " 00:00:00.0");
			if (currentTimestamp < selectedTimestamp) {
				Toast.makeText(getApplicationContext(),
						"Please Enter A Valid Date", Toast.LENGTH_LONG).show();
				DateBtn.performClick();
				return false;
			} else {
				values[0] = Pt_date.getText().toString().trim();
				values[1] = selectedTimestamp + "";
				values[2] = Pt_val1.getText().toString().trim();
				values[3] = Pt_val2.getText().toString().trim();
				values[4] = Pt_inr.getText().toString().trim();
				return true;
			}
		}
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDate();
		}
	};

	@SuppressWarnings("deprecation")
	private void updateDate() {
		Pt_date.setText(new StringBuilder().append(mYear).append("-")
				.append(getPadding(mMonth + 1)).append("-")
				.append(getPadding(mDay)));

		showDialog(DATE_DIALOG_ID);
	}

	public void GetTodaysDate() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		StringBuilder builder = new StringBuilder().append(mYear).append("-")
				.append(getPadding(mMonth + 1)).append("-")
				.append(getPadding(mDay));
		currentTimestamp = GetTimeStamp(builder + " 23:59:00.0");
	}

	public String getPadding(int val) {
		if (val > 9)
			return val + "";
		else
			return "0" + val;
	}

	public long GetTimeStamp(String strdate) {
		try {
			Timestamp timestamp = Timestamp.valueOf(strdate);
			return timestamp.getTime() / 1000;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	public static void ProcessOnClick(int position){
		
	}
	
}
