package com.tenig.capturephoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper
{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = " MapDetailsdb";
    // Contacts table name
    private static final String TABLE_LOCATIONS = " locations";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_lATTITUDE = "latititude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_IMAGE = "image";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_lATTITUDE + " REAL,"
                + KEY_LONGITUDE + " REAL,"
                + KEY_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read) Operations
     */

    public// Adding new locations
    void addLcations(Locations contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_lATTITUDE, contact.latitude); // Location Name
        values.put(KEY_LONGITUDE, contact.longitude); // Location Name
        values.put(KEY_IMAGE, contact.image); // Location Phone

        // Inserting Row
        db.insert(TABLE_LOCATIONS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
//    Location getContact(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_LOCATIONS, new String[] { KEY_ID,
//                        KEY_NAME, KEY_IMAGE }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Location contact = new Location(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getBlob(1));
//
//        // return contact
//        return contact;
//
//    }

     //Getting All Locations
    public List<Locations> getAllLocations() {
        List<Locations> locationList = new ArrayList<Locations>();
        // Select All Query
        String selectQuery = "SELECT  * FROM "+ TABLE_LOCATIONS +";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                    Locations locations=new Locations();
                    locations.setId(Integer.parseInt(cursor.getString(0)));
                    locations.setLatitude(cursor.getFloat(1));
                    locations.setLongitude(cursor.getFloat(2));
                    locations.setImage(cursor.getBlob(3));
                    locationList.add(locations);

            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return locationList;
    }
}
