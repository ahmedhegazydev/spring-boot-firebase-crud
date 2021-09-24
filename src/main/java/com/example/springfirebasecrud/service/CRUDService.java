package com.example.springfirebasecrud.service;


import com.example.springfirebasecrud.model.CRUD;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CRUDService {


    public CRUD getCrud(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFireStore =
                FirestoreClient.getFirestore();
        DocumentReference documentReference =
                dbFireStore.collection("crud_users")
                        .document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        CRUD crud;
        if (documentSnapshot.exists()){
            crud = documentSnapshot.toObject(CRUD.class);
            return crud;
        }
        return null;
    }

    public String createCrud(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFireStore =
                FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture =
                dbFireStore.collection("crud_users")
                        .document(crud.getName())
                        .set(crud);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String updateCrud() {
        return null;
    }

    public String deleteCrud(String documentId) {
        Firestore dbFireStore =
                FirestoreClient.getFirestore();
        ApiFuture<WriteResult> documentReference =
                dbFireStore.collection("crud_users")
                        .document(documentId)
                        .delete();

        return "Successfully deleted " + documentId;
    }
}
