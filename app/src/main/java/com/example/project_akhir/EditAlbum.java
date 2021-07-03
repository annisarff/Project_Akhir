package com.example.project_akhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_akhir.database.album;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditAlbum extends AppCompatActivity {
    TextView tv_id;
    EditText ed_nm, ed_hrg;
    Button btnEdit;
    DatabaseReference databaseReference;
    String kode,nama,harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_album);
        tv_id = findViewById(R.id.tv_id);
        ed_nm = findViewById(R.id.edNama);
        ed_hrg = findViewById(R.id.edHarga);
        btnEdit = findViewById(R.id.btEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("Kunci1");
        nama = bundle.getString("Kunci2");
        harga = bundle.getString("Kunci3");

        tv_id.setText(kode);
        ed_nm.setText(nama);
        ed_hrg.setText(harga);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAlbum(new album(ed_nm.getText().toString(),ed_hrg.getText().toString()));
            }
        });
    }
    public  void  editAlbum(album albm)
    {
        databaseReference.child("Album")
                .child(kode)
                .setValue(albm)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditAlbum.this, "Data Berhasil Diupdate",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}