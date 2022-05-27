package com.threebro.computerguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.RecommendedSet;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ProductList.db";
    public static final String AP_TABLE = "Product";
    public static final String AP_CREATE = "CREATE TABLE 'Product' "
            + "('Cpu' INTEGER, 'Gpu' INTEGER, 'MainBoard' INTEGER, 'Power' INTEGER,'Ram' INTEGER,'RamCapacity' INTEGER, 'RamAmount' INTEGER,'ComputerCase' INTEGER,'Storage' INTEGER,'StorageAmount' INTEGER,'Cooler' INTEGER,'TotPrice' INTEGER)";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AP_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + AP_CREATE);
        onCreate(db);
    }

    public boolean addProductList(FinalTwo List) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Cpu", List.getCpu().getIndex());
        cv.put("Gpu", List.getGpu().getIndex());
        cv.put("MainBoard", List.getMb().getIndex());
        cv.put("Power", List.getPw().getIndex());
        cv.put("Ram", List.getRm().getIndex());
        cv.put("RamCapacity", List.getRm().getRamCapacity());
        cv.put("RamAmount", List.getRm().getIndex());
        cv.put("ComputerCase", List.getCa().getIndex());
        cv.put("Storage", List.getSt().getIndex());
        cv.put("StorageAmount", List.getSt().getIndex());
        cv.put("Cooler", List.getCl().getIndex());
        cv.put("TotPrice", List.getPrice());
        db.insert(AP_TABLE, null, cv);
        System.out.println("Adding done");
        return true;
    }

    public String getResult(){
        SQLiteDatabase db = getReadableDatabase();
        String result ="";
        Cursor cursor = db.rawQuery("SELECT * FROM Product",null);
        PastModelListActivity.recommendListManager.recommendedSetList = new ArrayList<>();
        while (cursor.moveToNext()) {
            FinalTwo list = new FinalTwo();
            list.setCpu(MainActivity.desktopSet.getCPUList().get(cursor.getInt(0)));
            list.setGpu(MainActivity.desktopSet.getGPUList().get(cursor.getInt(1)));
            list.setMb(MainActivity.desktopSet.getMbList().get(cursor.getInt(2)));
            list.setPw(MainActivity.desktopSet.getPWList().get(cursor.getInt(3)));
            list.setRm(MainActivity.desktopSet.getRAMList().get(cursor.getInt(4)));
            list.getRm().setAmount(cursor.getInt(6));
            list.setCa(MainActivity.desktopSet.getCaseList().get(cursor.getInt(7)));
            list.setSt(MainActivity.desktopSet.getSTList().get(cursor.getInt(8)));
            list.getSt().setAmount(cursor.getInt(9));
            list.setCl(MainActivity.desktopSet.getCLList().get(cursor.getInt(10)));
            list.setPrice(cursor.getInt(11));
            PastModelListActivity.recommendListManager.addCompareList(list);
        }
        return result;
    }
}