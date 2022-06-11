package com.threebro.computerguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.RecommendedSet;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ProductList.db";//DATABASE name
    public static final String AP_TABLE = "Product";//Product Table name
    public static final String LAP_TABLE = "LapProduct";//Laptop Table name
    //Insert Taple value
    public static final String AP_CREATE = "CREATE TABLE 'Product' "
            + "('Cpu' INTEGER, 'Gpu' INTEGER, 'MainBoard' INTEGER, 'Power' INTEGER,'Ram' INTEGER,'RamCapacity' INTEGER, 'RamAmount' INTEGER,'ComputerCase' INTEGER,'Storage' INTEGER,'StorageAmount' INTEGER,'Cooler' INTEGER,'TotPrice' INTEGER)";
    public static final String LAP_CREATE = "CREATE TABLE 'LapProduct' "
            + "('LapTopIndex' INTEGER)";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AP_CREATE);
        db.execSQL(LAP_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + AP_CREATE);//기존에 테이블 존재할시에 drop하고 추가
        db.execSQL("DROP TABLE IF EXISTS " + LAP_CREATE);//기존에 테이블 존재할시에 drop하고 추가
        onCreate(db);
    }
    //Insert desktop component into cv and save it into db
    public boolean addProductList(FinalTwo List) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Cpu", List.getCpu().getIndex());
        cv.put("Gpu", List.getGpu().getIndex());
        cv.put("MainBoard", List.getMb().getIndex());
        cv.put("Power", List.getPw().getIndex());
        cv.put("Ram", List.getRm().getIndex());
        cv.put("RamCapacity", List.getRm().getRamCapacity());
        cv.put("RamAmount", List.getRm().getAmount());
        cv.put("ComputerCase", List.getCa().getIndex());
        cv.put("Storage", List.getSt().getIndex());
        cv.put("StorageAmount", List.getSt().getIndex());
        cv.put("Cooler", List.getCl().getIndex());
        cv.put("TotPrice", List.getPrice());
        db.insert(AP_TABLE, null, cv);
        System.out.println("Adding done");
        return true;
    }
    //Insert Laptop component into cv and save it into db
    public boolean addLabProductList(Laptop List) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("LapTopIndex", List.getIndex());
        db.insert(LAP_TABLE, null, cv);
        System.out.println("Adding done");
        return true;
    }

    //get Laptop List from db Table
    public String getLaptopResult(){
        SQLiteDatabase db = getReadableDatabase();
        String result ="";
        Cursor cursor = db.rawQuery("SELECT * FROM LapProduct",null);//Location of Cursor
        PastModelListActivity.recommendListManager.recommendLaptopSetList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Laptop list = new Laptop();
            list = MainActivity.laptopSet.getLaptopList().get(cursor.getInt(0));
            PastModelListActivity.recommendListManager.addLaptopCompareList(list);
        }
        return result;
    }

    //get Desktop List from db Table
    public String getResult(){
        SQLiteDatabase db = getReadableDatabase();
        String result ="";
        Cursor cursor = db.rawQuery("SELECT * FROM Product",null);
        PastModelListActivity.recommendListManager.recommendedSetList = new ArrayList<>();
        while (cursor.moveToNext()) {//getCursor and get from db line by line
            FinalTwo list = new FinalTwo();
            list.setCpu(MainActivity.desktopSet.getCPUList().get(cursor.getInt(0)));
            list.setGpu(MainActivity.desktopSet.getGPUList().get(cursor.getInt(1)));
            list.setMb(MainActivity.desktopSet.getMbList().get(cursor.getInt(2)));
            list.setPw(MainActivity.desktopSet.getPWList().get(cursor.getInt(3)));
            list.setRm(MainActivity.desktopSet.getRAMList().get(cursor.getInt(4)));
            int amount = cursor.getInt(6);
            list.getRm().setAmount(amount);
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