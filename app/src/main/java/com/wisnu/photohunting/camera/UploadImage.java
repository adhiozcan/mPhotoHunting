package com.wisnu.photohunting.camera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wisnu.photohunting.R;

public class UploadImage extends AppCompatActivity {
    private String category;
    private EditText edLocation;
    private EditText edDescription;

    private void submitPhoto() {
        String location = edLocation.getText().toString();
        String description = edDescription.getText().toString();
    }

    Spinner.OnItemSelectedListener categoryOnSelectedItem() {
        return new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    Button.OnClickListener mSubmitPhotoClickListener() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPhoto();
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spinner kategori = (Spinner) findViewById(R.id.kategori_foto);
        EditText lokasi = (EditText) findViewById(R.id.lokasi_foto);
        EditText keterangan = (EditText) findViewById(R.id.keterangan_foto);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        kategori.setOnItemSelectedListener(categoryOnSelectedItem());
        btnSubmit.setOnClickListener(mSubmitPhotoClickListener());
    }
}
