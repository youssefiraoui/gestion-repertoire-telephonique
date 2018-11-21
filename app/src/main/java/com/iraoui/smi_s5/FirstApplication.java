package com.iraoui.smi_s5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iraoui.smi_s5.entities.User;

public class FirstApplication extends AppCompatActivity {

    Button buttonLog;
    TextView inscrire;
    EditText email,pwd;
    Operations database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_application);

        database = new Operations(this);

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.password);

        buttonLog = findViewById(R.id.buttonLog);
        inscrire = findViewById(R.id.inscrire);

        buttonLog.setOnClickListener(new View.OnClickListener() {
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

                User user = database.getUser(
                        email.getText().toString(),//email
                        pwd.getText().toString()//password
                );
                if (user != null)
                {
                    Toast.makeText(FirstApplication.this,"Bienvenue",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FirstApplication.this,ListContacts.class);
                    startActivity( intent );

                }else {
                    Toast.makeText(FirstApplication.this,"Email ou password est érroné",Toast.LENGTH_SHORT).show();
                }
            }
        });

        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstApplication.this,"Inscription",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FirstApplication.this,Inscription.class);
                startActivity( intent );
            }
        });
    }
}
