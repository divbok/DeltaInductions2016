package com.example.user.delta3;

import android.net.Uri;

import java.net.URI;

/**
 * Created by user on 7/1/2016.
 */
public class Contacts {
    private String _name,_phone;
    private Uri _imguri;
    private int _id;


    public Contacts(int id,String name, String phone, Uri imguri) {
        this._id=id;
        this._name = name;
        this._phone= phone;
        this._imguri=imguri;
    }

    public int getId() {
        return _id;
    }

    public Uri getUri()
{
    return _imguri;
}
    public String getName() {
        return _name;
    }

    public String getPhone() {
        return _phone;
    }


}
