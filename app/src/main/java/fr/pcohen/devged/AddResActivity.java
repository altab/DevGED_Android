package fr.pcohen.devged;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fr.pcohen.devged.business.Search;
import fr.pcohen.devged.dao.DaoFireStore;

public class AddResActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner ddmType;
    private static final String[] paths = {"URL", "Text"};
    private String searchTitle;
    private String res;
    private Date date ;
    private List<String> tags;
    private String type;
    private EditText txtTitle;
    private EditText txtRes;
    private EditText txtTags;

    private void init(){

        txtTitle = findViewById(R.id.txtTitle);
        txtRes   = findViewById(R.id.txtRes);
        txtTags  = findViewById(R.id.txtTags);
        btnAddListener();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_res);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        init();

        ddmType = findViewById(R.id.ddmType);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(AddResActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddmType.setAdapter(adapter);
        ddmType.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                type = "URL";
                break;
            case 1:
               type = "text";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void btnAddListener(){


        (findViewById(R.id.btnAdd)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Log.d("test", "click");



                try {

                    AddResActivity.this.searchTitle = txtTitle.getText().toString();
                    AddResActivity.this.res = txtRes.getText().toString();
                    date = new Date() ;

                    String listeTags = txtTags.getText().toString();
                    String[] parts = listeTags.trim().split("\\s*,\\s*");
                    AddResActivity.this.tags=Arrays.asList(parts);


                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }

                //Controle donnees saisies
                if ( AddResActivity.this.searchTitle.isEmpty()
                        ||  AddResActivity.this.res.isEmpty()
                        ||  AddResActivity.this.tags.isEmpty()) {

                    Toast.makeText(AddResActivity.this, "Champ(s) vides(s)", Toast.LENGTH_SHORT).show();

                } else {

                    DaoFireStore daoFirestore = new DaoFireStore();
                    daoFirestore.addRes( createSearch());

                    String message = "Ressource : " + searchTitle +" Ajoutée";
                    Toast.makeText(AddResActivity.this, message , Toast.LENGTH_SHORT).show();

                    startActivity(getIntent());
                    finish();
                    
                }

            }
        });

    }

    private Search createSearch() {



        Search search = new Search();

        search.setTitle(this.searchTitle);
        search.setDate(this.date);
        search.setRes(this.res);
        search.setTags(this.tags);
        search.setType(this.type);

        Log.d("ENVOI", search.toString());

        return search;
    }


    public String getSearchTitle() {
        return this.searchTitle;
    }

    public void setSearchTitle(String title) {
        this.searchTitle = title;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
