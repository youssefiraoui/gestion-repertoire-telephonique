package com.iraoui.smi_s5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iraoui.smi_s5.entities.User;

import java.util.List;

public class Inscription extends AppCompatActivity {

    Button idButtonAnnuler, idInscrire;
    EditText nom, num, email, pwd;
    Operations database;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        database = new Operations(this);

        nom = findViewById(R.id.idNom);
        num = findViewById(R.id.idTel);
        email = findViewById(R.id.idEmail);
        pwd = findViewById(R.id.idPwd);


        idButtonAnnuler = findViewById(R.id.idButtonAnnuler);
        idInscrire = findViewById(R.id.idInscrire);

        idButtonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Inscription.this,"Inscription est annulée",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Inscription.this,FirstApplication.class);
                viderLesChamps();
                startActivity( intent );
            }
        });

        idInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().isEmpty()){
                    email.setError("Veuillez remplir ce champ");
                    email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Veuillez entrez un email valide");
                    email.requestFocus();
                    return;
                }

                if(pwd.getText().toString().isEmpty()){
                    pwd.setError("Veuillez remplir ce champ");
                    pwd.requestFocus();
                    return;
                }
                if(pwd.getText().toString().length()<6){
                    pwd.setError("veuillez entrer un mot de passe valide (minimum 6 chiffres)  ");
                    pwd.requestFocus();
                    return;
                }

                user = new User(nom.getText().toString(),//nom
                        num.getText().toString(),//num
                        email.getText().toString(),//email
                        pwd.getText().toString());//password
                long ins = database.insertUser(user);
                if (ins > 0)
                {
                    Toast.makeText(Inscription.this,"User inscrit",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Inscription.this,FirstApplication.class);
                    viderLesChamps();
                    startActivity( intent );
                }

            }
        });
    }

    public void viderLesChamps()
    {
        nom.setText("");
        num.setText("");
        email.setText("");
        pwd.setText("");
    }


}
