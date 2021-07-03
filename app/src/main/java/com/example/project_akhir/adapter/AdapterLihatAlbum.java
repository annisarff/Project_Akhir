package com.example.project_akhir.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_akhir.EditAlbum;
import com.example.project_akhir.LihatAlbum;
import com.example.project_akhir.MainActivity;
import com.example.project_akhir.R;
import com.example.project_akhir.database.album;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AdapterLihatAlbum  extends RecyclerView.Adapter<AdapterLihatAlbum.ViewHolder>{
    private ArrayList<album> daftarAlbum;
    private Context context;
    private DatabaseReference databaseReference;

    public AdapterLihatAlbum(ArrayList<album> daftarTeman, Context context) {
        this.daftarAlbum = daftarAlbum;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_data,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String kode, nama, telepon;
        kode = daftarAlbum.get(position).getKode();
        nama = daftarAlbum.get(position).getNama();
        telepon = daftarAlbum.get(position).getHarga();

        holder.tvAlbm.setText(nama);

        holder.tvAlbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pm = new PopupMenu(v.getContext(), v);
                pm.inflate(R.menu.popup_menu);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Bundle bundle = new Bundle();
                                bundle.putString("kunci1", kode);
                                bundle.putString("kunci2", nama);
                                bundle.putString("kunci3", telepon);

                                Intent intent = new Intent(v.getContext(), EditAlbum.class);
                                intent.putExtras(bundle);
                                v.getContext().startActivity(intent);

                                break;
                            case R.id.hapus:
                                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                                alertDlg.setTitle("Yakin" +nama+ "akan dihapus?");
                                alertDlg.setMessage("Tekan Ya untuk menghapus").setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                DeleteData(kode);
                                                Toast.makeText(v.getContext(),"Data " +nama+ " berhasil dihapus",Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                                v.getContext().startActivity(intent);
                                            }
                                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog adlg = alertDlg.create();
                                adlg.show();
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });
        holder.tvAlbm.setText(nama);
    }

    @Override
    public int getItemCount() {
        return daftarAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAlbm;

        ViewHolder(View v){
            super(v);
            tvAlbm = (TextView) v.findViewById(R.id.tv_albm);
        }
    }
    public void DeleteData(String kode){
        if (databaseReference !=null){
            databaseReference.child("Teman").child(kode).removeValue();
        }
    }
}


