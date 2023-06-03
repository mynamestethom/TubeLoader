package com.example.tubeloader.HistoryVideo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DatabaseName = "MyHistoryDownload";
    private static final int DatabaseVersion = 1;
    private static final String tableName = "Downloaded_videos";
    private static final String columnId = "id";
    private static final String columnTitle = "title";
    private static final String columnLink = "linkvideo";


    public DatabaseHelper(@Nullable Context context){
        super(context , DatabaseName , null , DatabaseVersion);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableName + " (" + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + columnTitle + " TEXT, " + columnLink + " Text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ tableName);
        onCreate(db);
    }

    public void addHistory(String title , String linkvideo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(columnTitle , title);
        cv.put(columnLink , linkvideo);

        long resultValue = db.insert(tableName,null, cv);

        if (resultValue == -1){
            Toast.makeText(context, "Данные в БД не добавлены", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Данные успешно добавлены в Историю", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readHistory(){
        String query = "SELECT * FROM " +  tableName;
        SQLiteDatabase database= this.getReadableDatabase();
        Cursor cursor = null;

        if (database != null){
            cursor = database.rawQuery(query,null);
        }
        return  cursor;

    }

    public void deleteAllHistory() {

        SQLiteDatabase database = this.getWritableDatabase();

        String query = "DELETE FROM " + tableName;
        database.execSQL(query);

    }

    public void updateHistory(String title, String linkvideo , String id){

        SQLiteDatabase database =  this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(columnTitle,title);
        cv.put(columnLink, linkvideo);

        long result  = database.update(tableName, cv,"id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Обновление не получилось", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Обновление прошло успешно", Toast.LENGTH_SHORT).show();
        }
    }
}
