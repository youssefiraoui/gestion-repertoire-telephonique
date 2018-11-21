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

public class AddContact extends AppCompatActivity {

    Button addC, annuButton;
    EditText nomE, emailE, telE;
    Contact contact;
    Operations operations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        operations = new Operations(this);

        nomE = findViewById(R.id.idN_P);
        emailE = findViewById(R.id.idEmail_ADD);
        telE = findViewById(R.id.idTEL_ADD);

        addC = findViewById(R.id.addC);
        annuButton = findViewById(R.id.annu);

        addC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nomE.getText().toString().isEmpty()){
                    nomE.setError("Veuillez remplir ce champ");
                    nomE.requestFocus();
                    return;
                }
                if(emailE.getText().toString().isEmpty()){
                    emailE.setError("Veuillez remplir ce champ");
                    emailE.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailE.getText().toString()).matches()){
                    emailE.setError("please enter a valid email");
                    emailE.requestFocus();
                    return;
                }

                if(telE.getText().toString().isEmpty()){
                    telE.setError("Veuillez remplir ce champ");
                    telE.requestFocus();
                    return;
                }
                if(telE.getText().toString().length()<10){
                    telE.setError("veuillez entrer un numéro valide (minimum 10 chiffres) ");
                    telE.requestFocus();
                    return;
                }

                String nom = nomE.getText().toString();
                String tel = telE.getText().toString();
                String email = emailE.getText().toString();

                contact = new Contact(nom,email,tel);
                operations.insertContact(contact);
                Toast.makeText(AddContact.this,"Contact Ajouter",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddContact.this,ListContacts.class);
                startActivity( intent );
            }
        });

        annuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddContact.this,"Add Contact Annuler ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddContact.this,ListContacts.class);
                startActivity( intent );
            }
        });
    }
}
