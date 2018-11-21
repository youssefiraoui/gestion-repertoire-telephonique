package com.iraoui.smi_s5;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.iraoui.smi_s5.entities.Contact;

public class DetailContact extends AppCompatActivity {

    TextView nomD, emailD, telD;
    ImageView imgD;
    Contact contact;
    int id;
    Operations operations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        operations = new Operations(this);

        nomD = findViewById(R.id.nomD);
        emailD = findViewById(R.id.emailD);
        telD = findViewById(R.id.telD);
        imgD = findViewById(R.id.imgD);

        id = (int) getIntent().getExtras().getInt("id");
        contact = operations.getContactByID(id);

        if (contact != null)
        {
            System.out.println("***************************** nom :" +contact.getNomEtPrenom() +" id : "+contact.getId());

            nomD.setText(contact.getNomEtPrenom());
            emailD.setText(contact.getEmail());
            telD.setText(contact.getTel());

            TextDrawable drawable = null;
            drawable = TextDrawable.builder()
                    .buildRect(contact.getNomEtPrenom().substring(0, 1).toUpperCase(),
                            Color.rgb(232, 180, 231));

            imgD.setImageDrawable(drawable);

        }else {
            System.out.println("walou");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_detail, menu);

        MenuItem modifier = menu.findItem(R.id.modifier);
        modifier.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (contact.getId() != 0)
                {
                    Intent intent = new Intent(getApplicationContext(), UpdateOrDelete.class);
                    intent.putExtra("id", contact.getId());
                    startActivity(intent);
                }
                return true;
            }
        });
        MenuItem supprimer = menu.findItem(R.id.supprimer);
        supprimer.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (contact.getId() != 0)
                {
                    operations.supprimerContact(contact);
                }

                Toast.makeText(DetailContact.this,"Supprimer contact",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ListContacts.class));
                return true;
            }
        });
        MenuItem deconnexion = menu.findItem(R.id.deconnexion);
        deconnexion.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(), FirstApplication.class));
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
