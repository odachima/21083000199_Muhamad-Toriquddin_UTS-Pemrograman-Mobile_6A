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


public class MainActivity extends AppCompatActivity {

    private Button buttonPickDate;
    private TextView textViewSelectedDate;
    Spinner kelamin;
    String[] jenis={"Laki-Laki","Perempuan"};
    private TextView viewage;
    private Button kumpul, btnreset;
    private TextView datatgllahir, datausia ;
    private Spinner dataklmn;
    private EditText datanik, datanama, dataemail,  datatmptlahir,dataalamat,databidang;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    private RadioButton datawarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //datepicker
        buttonPickDate = findViewById(R.id.buttontgllahir);
        textViewSelectedDate = findViewById(R.id.tgllahir);

        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;


                                textViewSelectedDate.setText(selectedDate);
                            }
                        }, year, month, dayOfMonth);


                datePickerDialog.show();
            }
        });

        //spinner
        kelamin = findViewById(R.id.jnsklmn);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,jenis);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kelamin.setAdapter(arrayAdapter);
        kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),jenis[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //lihat usia
        viewage = findViewById(R.id.usia);
        textViewSelectedDate.addTextChangedListener(dateOfBirthWatcher);


        //validasiinput&submit&reset
        kumpul = findViewById(R.id.submit);
        btnreset = findViewById(R.id.reset);
        datanik = findViewById(R.id.NIK);
        checkBox1 = findViewById(R.id.box1);
        checkBox2 = findViewById(R.id.box2);
        checkBox3 = findViewById(R.id.box3);
        checkBox4 = findViewById(R.id.box4);
        checkBox5 = findViewById(R.id.box5);
        datanama = findViewById(R.id.nama);
        dataemail = findViewById(R.id.email);
        datatgllahir = findViewById(R.id.tgllahir);
        datatmptlahir = findViewById(R.id.tmptlahir);
        dataalamat = findViewById(R.id.alamat);
        datausia = findViewById(R.id.usia);
        datawarga = findViewById(R.id.radio1);
        dataklmn = findViewById(R.id.jnsklmn);

        kumpul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validasi input dari EditText
                String inputText = datanik.getText().toString().trim();
                int angkanik = inputText.length();
                if (angkanik < 16 || angkanik > 16) {
                    // Tampilkan pesan kesalahan jika input kurang dari 16 karakter
                    datanik.setError("NIK harus 16 angka");
                    return;
                }

                // Mengumpulkan nilai dari CheckBox yang tercentang
                List<String> selectedOptions = new ArrayList<>();
                if (checkBox1.isChecked()) {
                    selectedOptions.add(checkBox1.getText().toString());
                }
                if (checkBox2.isChecked()) {
                    selectedOptions.add(checkBox2.getText().toString());
                }
                if (checkBox3.isChecked()) {
                    selectedOptions.add(checkBox3.getText().toString());
                }
                if (checkBox4.isChecked()) {
                    selectedOptions.add(checkBox4.getText().toString());
                }
                if (checkBox5.isChecked()) {
                    selectedOptions.add(checkBox5.getText().toString());
                }


                //edit text
                String inputnik = datanik.getText().toString().trim();
                String inputnama = datanama.getText().toString().trim();
                String inputalamat = dataalamat.getText().toString().trim();
                String inputemail = dataemail.getText().toString().trim();
                String inputtmptlahir = datatmptlahir.getText().toString().trim();

                //textview
                String inputusia = datausia.getText().toString().trim();
                String inputtgl = datatgllahir.getText().toString().trim();

                //spiner
                String inputklmn = dataklmn.getSelectedItem().toString();


                //radiobutton

                boolean radioButtonChecked = datawarga.isChecked();
                String selectedRadioButtonText = "WNA";

                if (radioButtonChecked) {
                    selectedRadioButtonText = datawarga.getText().toString();
                }

                // Membuat Intent untuk berpindah ke halaman kedua
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Menambahkan nilai dari setiap komponen ke dalam Intent
                intent.putExtra("niknya", inputnik);
                intent.putExtra("namanya", inputnama);
                intent.putExtra("emailnya", inputemail);
                intent.putExtra("tmptlhrnya", inputtmptlahir);
                intent.putExtra("alamatnya", inputalamat);
                intent.putExtra("usianya", inputusia);
                intent.putExtra("klmn", inputklmn);
                intent.putExtra("tglnya", inputtgl);
                intent.putExtra("radiobutton", selectedRadioButtonText);
                intent.putStringArrayListExtra("checkbox", (ArrayList<String>) selectedOptions);


                // Memulai aktivitas kedua
                startActivity(intent);
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengatur ulang status RadioButton ke keadaan awal (tidak terpilih)
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                datawarga.setChecked(false);
                datanik.setText("");
                datanama.setText("");
                dataemail.setText("");
                datatgllahir.setText("");
                datatmptlahir.setText("");
                dataalamat.setText("");
                datausia.setText("");

            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    };
    private final TextWatcher dateOfBirthWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Not used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Not used
        }

        @Override
        public void afterTextChanged(Editable s) {
            calculateAge();
        }
    };

    private void calculateAge() {
        String dobString = textViewSelectedDate.getText().toString();
        if (!dobString.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                Date dobDate = dateFormat.parse(dobString);
                if (dobDate != null) {
                    Calendar calDOB = Calendar.getInstance();
                    calDOB.setTime(dobDate);
                    Calendar calToday = Calendar.getInstance();
                    int age = calToday.get(Calendar.YEAR) - calDOB.get(Calendar.YEAR);
                    viewage.setText(age + " tahun");
                } else {
                    viewage.setText("Format tanggal tidak valid!");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                viewage.setText("Format tanggal tidak valid!");
            }
        } else {
            viewage.setText("Silakan masukkan tanggal lahir.");
        }
    }
    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}