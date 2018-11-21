package com.iraoui.smi_s5;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.iraoui.smi_s5.entities.Contact;
import com.iraoui.smi_s5.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListContacts extends AppCompatActivity {
    ListView listView;
    CostumList contactsView;
    List<Contact> contacts;
    Operations database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);
        listView = findViewById(R.id.idListContact);
        database = new Operations(this);

        contacts  = database.getAllContacts();
        /*
        Contact c = new Contact();
        c.setEmail("youssef@iraoui.com");
        c.setNomEtPrenom("Youssef IRAOUI");
        c.setTel("06 03 94 21 79");

        Contact c1 = new Contact();
        c1.setEmail("iraoui@iraoui.com");
        c1.setNomEtPrenom("Med Agoulzi");
        c1.setTel("06 11 12 13 14");

        Contact c2 = new Contact();
        c2.setEmail("doha@chkarat.com");
        c2.setNomEtPrenom("doha Chkarat");
        c2.setTel("06 31 09 97 91");

        contacts.add(c);
        contacts.add(c1);
        contacts.add(c2);
        for (Contact v: contacts)
        {
            System.out.println(v.getNomEtPrenom()+" "+v.getTel());
        }
       */
        contactsView = new CostumList(ListContacts.this,contacts);
        listView.setAdapter(contactsView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"item Cliked "+contacts.get(i).getNomEtPrenom(),Toast.LENGTH_SHORT).show();

                Contact contact = contacts.get(i);

                Intent intent = new Intent(getApplicationContext(),DetailContact.class);
                intent.putExtra("id", contact.getId());
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_list, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem) ;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                ArrayList<Contact> contactFiltrees = new ArrayList<>();

                for( Contact temp : contacts)
                {
                    if (temp.getNomEtPrenom().toLowerCase().contains(s.toLowerCase()))
                    {
                        contactFiltrees.add(temp);
                    }
                }
                listView.setAdapter(new CostumList(ListContacts.this,contactFiltrees));
                return true;
            }

        });
        MenuItem addClient = menu.findItem(R.id.addContact);
        addClient.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(), AddContact.class));
                return true;
            }
        });
        MenuItem deconnexion = menu.findItem(R.id.deconnexion);
        deconnexion.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
               /*
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("connected", false);
                editor.commit();*/
                startActivity(new Intent(getApplicationContext(), FirstApplication.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
