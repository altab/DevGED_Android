package fr.pcohen.devged;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import fr.pcohen.devged.business.Search;


public class SearchActivity extends AppCompatActivity {

    private EditText searchText;
    private Button btnSearch;
    private List<Search> searchList;

    // Access a Cloud Firestore instance
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("TEST", db.collection("/DevGED").getId());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void init() {
        searchText = (EditText)findViewById(R.id.searchText);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        ecouteSearch();
    }

    private void ecouteSearch(){

        btnSearch.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                String search;

                try {
                    search = searchText.getText().toString();
                    if( search.isEmpty() ||  search == null) {
                        Toast.makeText(SearchActivity.this, getString(R.string.emptyField), Toast.LENGTH_SHORT).show();
                    } else {
                        textSearch(search);
                    }
                } catch (Exception e) {
                    Toast.makeText(SearchActivity.this, "Probleme saisie", Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }

    private void textSearch(String search){

        Toast.makeText(SearchActivity.this, search, Toast.LENGTH_SHORT).show();

        db.collection("/DevGED").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if(!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            Log.d("TEST", Integer.toString(list.size()));

                            for(DocumentSnapshot d : list) {

                                Search s = d.toObject(Search.class);
                                Log.d("TEST", s.toString());
                                //getSearchList().add(s);

                            }

                        }
                    }
                });

        //Log.d("Liste", this.searchList.toString());

    }

    public EditText getSearchText() {
        return this.searchText;
    }

    public Button getBtnSearch() {
        return this.btnSearch;
    }

    public List<Search> getSearchList() {
        return this.searchList;
    }
}
