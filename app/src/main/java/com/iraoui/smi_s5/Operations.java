package com.iraoui.smi_s5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.iraoui.smi_s5.entities.Contact;
import com.iraoui.smi_s5.entities.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IRAOUI on 20/11/2018.
 */

public class Operations extends SQLiteOpenHelper {

    private static final String NOM_DATABASE = "contacts.db";
    private static final int DB_VERSION = 1;
    Context context;

    public Operations(Context context) {

        super(context, NOM_DATABASE, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql_USER_TABLE = "CREATE TABLE USER (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, NUM TEXT, EMAIL TEXT, PASSWORD TEXT ) ;";
        String sql_CONTACT_TABLE = "CREATE TABLE CONTACT (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, TEL TEXT, EMAIL TEXT);";

        sqLiteDatabase.execSQL(sql_USER_TABLE);
        sqLiteDatabase.execSQL(sql_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql_USER_TABLE = "DROP TABLE IF EXISTS USER";
        String sql_CONTACT_TABLE = "DROP TABLE IF EXISTS USER";

        sqLiteDatabase.execSQL(sql_USER_TABLE);
        sqLiteDatabase.execSQL(sql_CONTACT_TABLE);

        onCreate(sqLiteDatabase);

    }

    public long insertUser(User user) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NOM", user.getNom());
        values.put("NUM", user.getNum());
        values.put("EMAIL", user.getEmail());
        values.put("PASSWORD", user.getPwd());

        long row = database.insert("USER", null, values);
        database.close();
        return row;
    }



    public User getUser(String email, String password) {

        SQLiteDatabase database = this.getReadableDatabase();
        User user = null;

        Cursor cursor = database.query("USER",
                new String[]{"NOM", "NUM", "EMAIL", "PASSWORD"},
                "email = ? and password = ?",
                new String[]{email, password},
                null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new User(cursor.getString(cursor.getColumnIndex("NOM")),
                    cursor.getString(cursor.getColumnIndex("NUM")),
                    cursor.getString(cursor.getColumnIndex("EMAIL")),
                    cursor.getString(cursor.getColumnIndex("PASSWORD")));
        }
        return user;
    }



    public List<User> getAllUser(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query("USER", null, null, null, null, null, null, null);

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<User> studentList = new ArrayList<>();
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("NOM"));
                        String tel = cursor.getString(cursor.getColumnIndex("NUM"));
                        String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
                        String pwd = cursor.getString(cursor.getColumnIndex("PASSWORD"));

                        studentList.add(new User( name, tel, email, pwd));
                    }   while (cursor.moveToNext());

                    return studentList;
                }
        } catch (Exception e){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public long insertContact(Contact contact)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NOM",contact.getNomEtPrenom());
        values.put("EMAIL",contact.getEmail());
        values.put("TEL",contact.getTel());

        long row = database.insert("CONTACT",null,values);
        database.close();
        return row;

    }

    public List<Contact> getAllContacts()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = database.query("CONTACT",null, null, null, null, null, null, null);
            if (cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    List<Contact> contacts = new ArrayList<>();
                    do {
                        String nom = cursor.getString(cursor.getColumnIndex("NOM"));
                        String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
                        String tel = cursor.getString(cursor.getColumnIndex("TEL"));
                        int id = cursor.getInt(cursor.getColumnIndex("ID"));

                        Contact contact = new Contact();
                        contact.setNomEtPrenom(nom);
                        contact.setTel(tel);
                        contact.setEmail(email);
                        contact.setId(id);

                        contacts.add(contact);
                    }while (cursor.moveToNext());
                    return contacts;
                }
            }
        }catch (Exception e){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            database.close();
        }
        return Collections.emptyList();
    }

    public void supprimerContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("CONTACT",
                 "ID=?",
                new String[]{String.valueOf(contact.getId())}
        );
        sqLiteDatabase.close();
    }

    public void updateContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NOM",contact.getNomEtPrenom());
        values.put("TEL",contact.getTel());
        values.put("EMAIL",contact.getEmail());

        sqLiteDatabase.update("CONTACT",values,
                "ID=?",
                new String[]{String.valueOf(contact.getId())}
        );
        sqLiteDatabase.close();
    }

    public Contact getContactByID( int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("CONTACT",null,
                 "ID=?",
                new String[]{String.valueOf(id)},
                null,null,null,"1");

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Contact contact = new Contact(
                cursor.getString(cursor.getColumnIndex("NOM")),
                cursor.getString(cursor.getColumnIndex("EMAIL")),
                cursor.getString(cursor.getColumnIndex("TEL")));
        contact.setId(cursor.getInt(cursor.getColumnIndex("ID")));
        return contact;
    }
}
