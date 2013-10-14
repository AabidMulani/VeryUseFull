package in.ankita.dadsrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ab_DB extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "inrreportdb";
	private static final String TABLE_recordsS = "reports";
	private static final String KEY_ID = "id";
	private static final String KEY_DATE = "date";
	private static final String KEY_PT1 = "pt1";
	private static final String KEY_PT2 = "pt2";
	private static final String KEY_INR = "inr";

	public Ab_DB(Context context, CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_records_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_recordsS + "("
				+ KEY_ID + " TEXT PRIMARY KEY," + KEY_DATE + " TEXT,"
				+ KEY_PT1 + " TEXT," + KEY_PT2 + " TEXT," + KEY_INR + " TEXT"
				+ ")";
		db.execSQL(CREATE_records_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_recordsS);
		onCreate(db);
	}

	public boolean AddThisEntry(String[] data) {
		ContentValues values = new ContentValues();
		values.put(KEY_ID, data[0]);
		values.put(KEY_DATE, data[1]);
		values.put(KEY_PT1, data[2]);
		values.put(KEY_PT2, data[3]);
		values.put(KEY_INR, data[4]);
		SQLiteDatabase db = this.getWritableDatabase();
		boolean result = db.insert(TABLE_recordsS, null, values) != -1;

		db.close();
		return result;
	}

	public int GetAll() {
		try {
			String query = "Select * FROM " + TABLE_recordsS;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(query, null);
			int length = cursor.getColumnCount();
			
			if (cursor.moveToFirst()) {
				do {
					BaseApplication.IDs.add(cursor.getString(0));
					Log.v("ID", cursor.getString(0));
					BaseApplication.TimeStamps.add(cursor.getString(1));
					Log.v("TIMESTAMP", cursor.getString(1));
					BaseApplication.PT_Val1.add(cursor.getString(2));
					Log.v("VAL 1", cursor.getString(2));
					BaseApplication.PT_Val2.add(cursor.getString(3));
					Log.v("VAL 2", cursor.getString(3));
					BaseApplication.PT_INR.add(cursor.getString(4));
					Log.v("INR", cursor.getString(4));
				} while (cursor.moveToNext());
			}
			db.close();
			return length;
		} catch (Exception ex) {
			ex.toString();
			return -1;
		}
	}

//	public boolean deleteProduct(String productname) {
//
//		boolean result = false;
//
//		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE "
//				+ COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";
//
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		Cursor cursor = db.rawQuery(query, null);
//
//		Product product = new Product();
//
//		if (cursor.moveToFirst()) {
//			product.setID(Integer.parseInt(cursor.getString(0)));
//			db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
//					new String[] { String.valueOf(product.getID()) });
//			cursor.close();
//			result = true;
//		}
//		db.close();
//		return result;
//	}

}
