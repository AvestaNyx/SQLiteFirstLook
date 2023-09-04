package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Footballers", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS footballers (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");

            //database.execSQL("INSERT INTO footballers (name, age) VALUES ('Messi', 36)");
            //database.execSQL("INSERT INTO footballers (name, age) VALUES ('Icardi', 30)");
            //database.execSQL("INSERT INTO footballers (name, age) VALUES ('Zaha', 29)");

            //database.execSQL("UPDATE footballers SET age = 31 WHERE name = 'Icardi'");
            //database.execSQL("UPDATE footballers SET name = 'Mauro Icardi' WHERE id = 2 ");

            database.execSQL("DELETE FROM footballers WHERE id = 3");

            Cursor cursor = database.rawQuery("SELECT * FROM footballers WHERE name = 'Icardi'", null);
            
            //Cursor cursor = database.rawQuery("SELECT * FROM footballers WHERE name LIKE 'I%'", null);
            //Cursor cursor = database.rawQuery("SELECT * FROM footballers WHERE name LIKE '%i'", null);

            //Cursor cursor = database.rawQuery("SELECT * FROM footballers", null);


            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIx));
            }

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}