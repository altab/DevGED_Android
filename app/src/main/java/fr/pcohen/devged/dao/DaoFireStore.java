package fr.pcohen.devged.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fr.pcohen.devged.business.Search;

public class DaoFireStore {

    // Access a Cloud Firestore instance
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Create
    public void addRes( Search search) {

        boolean success =false;


        db.collection("/DevGED")
                .add(searchToMap(search))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    private boolean success;

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("ENVOI", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ENVOI", "Error adding document", e);

                    }
                });
    }
    /*
    Map<String, Object> map = new HashMap<>();
    String[] array = {"One", "Two", "Three"};
    map.put("array", Arrays.asList(array));
    dayFormDoc.update(map);
    */

    public Map<String, Object> searchToMap(Search search){

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("title", search.getTitle());
        searchMap.put("res", search.getRes());
        searchMap.put("date", search.getDate());
        searchMap.put("type", search.getType());
        searchMap.put("tags", search.getTags());

        return searchMap;

    }


    // Update

    // Delete

    // Read





}
