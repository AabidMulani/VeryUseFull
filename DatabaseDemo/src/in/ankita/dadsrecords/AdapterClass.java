package in.ankita.dadsrecords;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterClass extends BaseAdapter {
	Context mContext; // ADD THIS to keep a context
	private LayoutInflater inflater;

	public AdapterClass(Context context) {
		this.mContext = context;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return BaseApplication.IDs.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		TextView date;
		TextView val1;
		TextView val2;
		TextView inr;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		try {
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.list_inflater, null);
				holder.date = (TextView) convertView.findViewById(R.id.lv_date);
				holder.val1 = (TextView) convertView.findViewById(R.id.lv_pt1);
				holder.val2 = (TextView) convertView.findViewById(R.id.lv_pt2);
				holder.inr = (TextView) convertView.findViewById(R.id.lv_inr);
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			holder.date.setText(BaseApplication.IDs.get(position));
			holder.val1.setText(BaseApplication.PT_Val1.get(position));
			holder.val2.setText(BaseApplication.PT_Val2.get(position));
			holder.inr.setText(BaseApplication.PT_INR.get(position));
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Log.v("Debug", "In Onclick");
					MainActivity.ProcessOnClick(position);
				}
			});

		} catch (Exception e) {
			Log.e("StakeHolders/getView()", e.toString());
			e.printStackTrace();
		}
		return convertView;
	}
}