package com.estem.applicationweither;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class Helper extends SQLiteOpenHelper {

    public Helper(@Nullable Context context) {
        super(context, "villeManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE villeConsulter(id INTERGER PRIMARY KEY,Ville TEXT, temperature TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS villeConsulter");
        onCreate(db);
    }
    public void insertVille(Ville v){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("Ville", v.getVille());
        cv.put("temperature",v.getTemperature());
        db.insert("villeConsulter",null,cv);
        db.close();
    }

    public void updateVille(Ville v){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Ville",v.getVille());
        cv.put("temperature",v.getTemperature());
        db.update("villeConsulter",cv,"id=?",new String[]{String.valueOf(v.getId())});
        db.close();
    }
    public void deleteVille(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("villeConsulter","id=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public List<Ville> getAllVille(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<Ville> villes=new LinkedList<Ville>();
                String query="SELECT * FROM villeConsulter";
                Cursor c=db.rawQuery(query,null);
                Ville v = null;
                if(c.moveToFirst()){
                    do {
                        v= new Ville();
                      //  v.setId(Integer.parseInt(c.getString(0)));
                        v.setVille(c.getString(1));
                        v.setTemperature(c.getString(2));
                        villes.add(v);
                    }while(c.moveToNext());
                }
        return villes;
    }
    public Ville getOneVille(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c =db.query("villeConsulter",
                new String[]{"Ville","temperature"},
                "id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(c!=null) c.moveToFirst();
        Ville v = new Ville(c.getInt(0),c.getString(1),c.getString(2));
        return v;
    }
}


