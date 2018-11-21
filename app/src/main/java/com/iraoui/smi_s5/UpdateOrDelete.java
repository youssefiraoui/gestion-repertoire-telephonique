package com.iraoui.smi_s5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iraoui.smi_s5.entities.Contact;

public class UpdateOrDelete extends AppCompatActivity {

    Button update, annuler;
    Contact contact;
    EditText nomU, emailU, telU;
    Operations database;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_or_delete);

        database = new Operations(this);

        nomU = findViewById(R.id.idNomUp);
        emailU = findViewById(R.id.idEmailUp);
        telU = findViewById(R.id.idTelUp);

        update = findViewById(R.id.idModifierUp);
        annuler = findViewById(R.id.idAnnUp);

        id = (int) getIntent().getExtras().getInt("id");
        contact = database.getContactByID(id);
        if (contact != null)
        {
            nomU.setText(contact.getNomEtPrenom());
            emailU.setText(contact.getEmail());
            telU.setText(contact.getTel());
        }


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nomU.getText().toString().isEmpty()){
                    nomU.setError("Veuillez remplir ce champ");
                    nomU.requestFocus();
                    return;
                }
                if(emailU.getText().toString().isEmpty()){
                    emailU.setError("Veuillez remplir ce champ");
                    emailU.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailU.getText().toString()).matches()){
                    emailU.setError("please enter a valid email");
                    emailU.requestFocus();
                    return;
                }

                if(telU.getText().toString().isEmpty()){
                    telU.setError("Veuillez remplir ce champ");
                    telU.requestFocus();
                    return;
                }
                if(telU.getText().toString().length()<10){
                    telU.setError("veuillez entrer un numéro valide (minimum 10 chiffres) ");
                    telU.requestFocus();
                    return;
                }

               String nom = nomU.getText().toString();
               String email = emailU.getText().toString();
               String tel = telU.getText().toString();

               contact.setTel(tel);
               contact.setNomEtPrenom(nom);
               contact.setEmail(email);

                try {

                    database.updateContact(contact);

                    Toast.makeText(UpdateOrDelete.this,"Contact modifié",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateOrDelete.this,DetailContact.class);
                    intent.putExtra("id", contact.getId());
                    startActivity( intent );

                }catch (Exception e)
                {
                    Toast.makeText(UpdateOrDelete.this,"Erreur qlq part",Toast.LENGTH_SHORT).show();
                    System.out.print(e.getStackTrace());
                }



            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateOrDelete.this,"Annuler -> Details",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateOrDelete.this,DetailContact.class);
                startActivity( intent );
            }
        });
    }
}
