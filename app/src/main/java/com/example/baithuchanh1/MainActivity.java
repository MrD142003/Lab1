package com.example.baithuchanh1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtHoten, edtSdt;
    Button btnAdd, btnSelect;
    ListView listViewSV;
    RadioGroup rdnGioitinh;
    Spinner spnQuequan;
    ImageView imgView;
    CheckBox chkDabong, chkXemphim, chkNghenhac;
    ArrayList<String> sinhVienList;
    ArrayAdapter adapter;
    Uri selectedImageUri;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntiWidget();
        initSpinner();
        initListView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtHoten.getText().toString();
                String sdt = edtSdt.getText().toString();
                String gioiTinh = ((RadioButton)findViewById(rdnGioitinh.getCheckedRadioButtonId())).getText().toString();
                String queQuan = spnQuequan.getSelectedItem().toString();
                String sothich = "";
                if(chkDabong.isChecked())
                    sothich += chkDabong.getText().toString()+" ";
                if(chkXemphim.isChecked())
                    sothich += chkXemphim.getText().toString()+" ";
                if(chkNghenhac.isChecked())
                    sothich += chkNghenhac.getText().toString();
                String thongtin = ten + " - " + gioiTinh + " - " + sdt + " - " + queQuan + "\n" + sothich;
                sinhVienList.add(thongtin);
                adapter.notifyDataSetChanged();
                clearInputs();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            selectedImageUri = data.getData();
            imgView.setImageURI(selectedImageUri);
            imgView.setVisibility(View.VISIBLE);
        }
    }

    private void clearInputs(){
        edtHoten.setText("");
        edtSdt.setText("");
        edtHoten.requestFocus();
        edtSdt.requestFocus();
        rdnGioitinh.clearCheck();
        chkNghenhac.setChecked(false);
        chkXemphim.setChecked(false);
        chkDabong.setChecked(false);
        imgView.setVisibility(View.GONE);
        selectedImageUri = null;
    }
    private void initListView() {
        sinhVienList = new ArrayList<>();
        adapter = new ArrayAdapter(this, R.layout.item, sinhVienList);
        listViewSV.setAdapter(adapter);
    }

    private void initSpinner(){
        String[] quequan = getResources().getStringArray(R.array.quequan);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item,quequan);
        spnQuequan.setAdapter(adapter);
    }
    private void IntiWidget() {
        edtHoten = findViewById(R.id.edtHoten);
        edtSdt = findViewById(R.id.edtSdt);
        rdnGioitinh = findViewById(R.id.rdnGioitinh);
        btnAdd = findViewById(R.id.btnAdd);
        listViewSV = findViewById(R.id.lview);
        spnQuequan = findViewById(R.id.spQuequan);
        chkDabong = findViewById(R.id.chkDabong);
        chkXemphim = findViewById(R.id.chkXemphim);
        chkNghenhac = findViewById(R.id.chkNghenhac);
        imgView = findViewById(R.id.imgView);
        btnSelect = findViewById(R.id.btnSelect);
    }
}