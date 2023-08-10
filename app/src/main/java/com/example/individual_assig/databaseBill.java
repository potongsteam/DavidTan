package com.example.individual_assig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class databaseBill extends SQLiteOpenHelper {

    private static final String Database_Name = "database_bill";
    private static final int Database_Version = 1;

    private static final String Table_Bills = "bills";
    private static final String ID = "id";
    private static final String TotalBill = "total_bills (RM)";
    private static final String NumofPeople = "Number of People";
    private static final String Breakdown_type = "breakdown_type";
    private static final String CustShare = "custom_shares";

    public databaseBill(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + Table_Bills + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TotalBill + " REAL, " +
                NumofPeople + " INTEGER, " +
                Breakdown_type + " TEXT, " +
                CustShare + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Upgrade(SQLiteDatabase db, int old, int New) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Bills);
        onCreate(db);
    }

    public long billInsert(double TotalAmount, int numOfPar,
                           String breakdownType, String CustomShares)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(TotalBill, TotalAmount);
        value.put(NumofPeople, numOfPar);
        value.put(Breakdown_type, breakdownType);
        value.put(CustShare, CustomShares);

        long id = db.insert(Table_Bills, null, value);
                db.close();
                return id;
    }
    public List<itemBill>getBills()
    {
        List<itemBill> list = new ArrayList<>();
        String chooseQuery = "SELECT * FROM " + Table_Bills;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(chooseQuery, null);

        if(cur.moveToFirst())
        {
            do {
                {
                    int id = cur.getInt(cur.getColumnIndex(ID));
                    double TotalAmount = cur.getDouble(cur.getColumnIndex(TotalBill));
                    int numOfPeople = cur.getInt(cur.getColumnIndex(NumofPeople));
                    String breakdown = cur.getString(cur.getColumnIndex(Breakdown_type));
                    String CustomShare = cur.getString(cur.getColumnIndex(CustShare));

                    itemBill bill = new itemBill(id, TotalAmount,numOfPeople, breakdown, CustomShare);
                    billList.add(bill);
                }
            }while (cur.moveToNext());

            cur.close();
            db.close();
            return billList;

        }
    }


}
