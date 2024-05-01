package com.app.utspemrogramanmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.util.Locale;
import androidx.appcompat.app.AlertDialog;
import android.widget.CheckBox;

import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import android.widget.RadioButton;

public class MainActivity2 extends AppCompatActivity {

    private Button back;
    private TextView viewnik, viewnama, viewemail, viewtgllahir, viewtmptlahir, viewalamat,viewusia, viewjnsklmn,viewkewarganegaraan,viewbidang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        //btnback
        back = findViewById(R.id.kembali);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        });

        //tampilkan semua
        viewnik = findViewById(R.id.nikview);
        viewnama = findViewById(R.id.namaview);
        viewemail = findViewById(R.id.emailview);
        viewtgllahir = findViewById(R.id.tgllahirview);
        viewtmptlahir = findViewById(R.id.tmptlahirview);
        viewalamat = findViewById(R.id.alamatview);
        viewusia = findViewById(R.id.usiaview);
        viewjnsklmn = findViewById(R.id.jnsklmnview);
        viewkewarganegaraan = findViewById(R.id.kewarganegaraanview);
        viewbidang = findViewById(R.id.bidangkompetensiview);

        Intent intent = getIntent();
        String nik = intent.getStringExtra("niknya");
        String nama = intent.getStringExtra("namanya");
        String email = intent.getStringExtra("emailnya");
        String tmptlhr = intent.getStringExtra("tmptlhrnya");
        String alamat = intent.getStringExtra("alamatnya");
        String usia = intent.getStringExtra("usianya");
        String klmn = intent.getStringExtra("klmn");
        String tgl = intent.getStringExtra("tglnya");
        String radio = intent.getStringExtra("radiobutton");
        ArrayList<String> checkbox = intent.getStringArrayListExtra("checkbox");


        viewnik.setText(nik);
        viewnama.setText(nama);
        viewalamat.setText(alamat);
        viewemail.setText(email);
        viewtgllahir.setText(tgl);
        viewtmptlahir.setText(tmptlhr);
        viewusia.setText(usia);
        viewjnsklmn.setText(klmn);
        viewkewarganegaraan.setText(radio);

        if (checkbox != null && !checkbox.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String option : checkbox) {
                sb.append(option).append("\n");
            }
            viewbidang.setText(checkbox.toString().trim());
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}