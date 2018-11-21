package com.iraoui.smi_s5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.iraoui.smi_s5.entities.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IRAOUI on 20/11/2018.
 */

public class CostumList extends ArrayAdapter<Contact> {

    private Context context;
    private List<Contact> contacts;

    public CostumList(Context context, List<Contact> contacts) {
        super(context,0, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contact card = contacts.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }

        TextView nom = convertView.findViewById(R.id.line1);
        TextView num = convertView.findViewById(R.id.line2);
        ImageView imgUser = convertView.findViewById(R.id.imgUser);

        nom.setText(card.getNomEtPrenom());
        num.setText(card.getTel());

        /*
        Showing contact name with initial letter in circle
         */
        TextDrawable drawable = null;
        drawable = TextDrawable.builder()
                .buildRect(card.getNomEtPrenom().substring(0, 1).toUpperCase(),
                        Color.rgb(232, 180, 231));

        imgUser.setImageDrawable(drawable);

        return convertView;
    }

}
