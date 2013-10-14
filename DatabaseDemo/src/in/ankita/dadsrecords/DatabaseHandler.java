//package in.ankita.dadsrecords;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHandler extends SQLiteOpenHelper {
//
//	// All Static variables
//	// Database Version
//	private static final int DATABASE_VERSION = 1;
//
//	// Database Name
//	private static final String DATABASE_NAME = "inrreportdb";
//
//	// recordss table name
//	private static final String TABLE_recordsS = "reports";
//
//	// recordss Table Columns names
//	private static final String KEY_ID = "id";
//	private static final String KEY_DATE = "date";
//	private static final String KEY_PT1 = "pt1";
//	private static final String KEY_PT2 = "pt2";
//	private static final String KEY_INR = "inr";
//	
//	
//	public DatabaseHandler(Context context) {
//		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//	}
//
//	// Creating Tables
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		String CREATE_recordsS_TABLE = "CREATE TABLE " + TABLE_recordsS + "("
//				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT,"
//				+ KEY_PT1 + " TEXT" + KEY_PT2 + " TEXT,"
//				+KEY_INR + " TEXT,"+")";
//		db.execSQL(CREATE_recordsS_TABLE);
//	}
//
//	// Upgrading database
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		// Drop older table if existed
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_recordsS);
//
//		// Create tables again
//		onCreate(db);
//	}
//
//	/**
//	 * All CRUD(Create, Read, Update, Delete) Operations
//	 */
//
//	// Adding new records
//	void addrecords(records records) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_DATE, records.getDate()); // records date
//		values.put(KEY_PT1, records.getpt1()); // records pt1
//		values.put(KEY_PT2, records.getpt2()); // records pt2
//		values.put(KEY_INR, records.getinr()); // records inr
//
//		// Inserting Row
//		db.insert(TABLE_recordsS, null, values);
//		db.close(); // Closing database connection
//	}
//
//	// Getting single records
//	records getrecords(int id) {
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		Cursor cursor = db.query(TABLE_recordsS, new String[] { KEY_ID,
//				KEY_DATE, KEY_PT1 }, KEY_ID + "=?",
//				new String[] { String.valueOf(id) }, null, null, null, null);
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		records records = new records(Integer.parseInt(cursor.getString(0)),
//				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
//		// return records
//		return records;
//	}
//	
//	// Getting All recordss
//	public List<records> getAllrecordss() {
//		List<records> recordsList = new ArrayList<records>();
//		// Select All Query
//		String selectQuery = "SELECT  * FROM " + TABLE_recordsS;
//
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//
//		// looping through all rows and adding to list
//		if (cursor.moveToFirst()) {
//			do {
//				records records = new records();
//				records.setID(Integer.parseInt(cursor.getString(0))); //add id
//				records.setdate(cursor.getString(1)); //add date
//				records.setpt1(cursor.getString(2));//add pt1
//				records.setpt2(cursor.getString(3));//add pt2
//				records.setinr(cursor.getString(4));//add inr
//				// Adding records to list
//				recordsList.add(records);
//			} while (cursor.moveToNext());
//		}
//
//		// return records list
//		return recordsList;
//	}
//
//	// Updating single records
//	public int updaterecords(records records) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_DATE, records.getDate());
//		values.put(KEY_PT1, records.getpt1());
//		values.put(KEY_PT2, records.getpt2());
//		values.put(KEY_INR, records.getinr());
//
//		// updating row
//		return db.update(TABLE_recordsS, values, KEY_ID + " = ?",
//				new String[] { String.valueOf(records.getID()) });
//	}
//
//	// Deleting single records
//	public void deleterecords(records records) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.delete(TABLE_recordsS, KEY_ID + " = ?",
//				new String[] { String.valueOf(records.getID()) });
//		db.close();
//	}
//
//
//	// Getting recordss Count
//	public int getrecordssCount() {
//		String countQuery = "SELECT  * FROM " + TABLE_recordsS;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		cursor.close();
//
//		// return count
//		return cursor.getCount();
//	}
//
//	class records{
//		
//	}
//
//}
