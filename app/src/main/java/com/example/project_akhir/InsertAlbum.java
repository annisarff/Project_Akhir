package com.example.project_akhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_akhir.database.album;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertAlbum extends AppCompatActivity {
    private EditText iNama, iHarga;
    private Button submitBtn;
    private DatabaseReference database;
    String nm, hrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_album);


        iNama = findViewById(R.id.inNama);
        iHarga = findViewById(R.id.inHarga);
        submitBtn = findViewById(R.id.btnOk);

        database = FirebaseDatabase.getInstance().getReference();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(iNama.getText().toString().isEmpty()) && !(iHarga.getText().toString().isEmpty())){
                    nm = iNama.getText().toString();
                    hrg = iHarga.getText().toString();

                    submitAlbum(new album(nm,hrg));

                }
                else
                    Toast.makeText(InsertAlbum.this, "Data tidak boleh kosong",Toast.LENGTH_SHORT).show();


            }
        });

    }



    private void  submitAlbum(album albm){
        database.child("Teman").push().setValue(albm).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                iNama.setText("");
                iHarga.setText("");
                Toast.makeText(InsertAlbum.this, "Data sukses ditambahkan", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

