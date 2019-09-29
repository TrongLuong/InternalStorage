package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnsave, btnread, btnclear;
    private TextView textView;
    private EditText txtMuti;
    private final String filename ="trong.txt";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnread = findViewById(R.id.btnread);
        btnsave = findViewById(R.id.btnsave);
        btnclear = findViewById(R.id.btnclear);
        textView = findViewById(R.id.txt);
        txtMuti = findViewById(R.id.txtMuti);
        btnsave.setOnClickListener(this);
        btnread.setOnClickListener(this);
        btnclear.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsave:
                saveData();

                break;
            case R.id.btnread:
                reaData();
                break;
            case  R.id.btnclear:
                txtMuti.setText("");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }

    }
    public void saveData(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);//che do default chi có ứng dụng ms dùng đc
             String x = txtMuti.getText().toString();
            fileOutputStream.write(x.getBytes());
            fileOutputStream.close();//luu thanh cong thi dong luong da mo ra
            Toast.makeText(this, "Luu thanh cong",Toast.LENGTH_SHORT).show();
            textView.setText("Filepath: "+  getFilesDir().getAbsolutePath());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void reaData(){
        try {
            FileInputStream fileInputStream = openFileInput(filename);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuffer buffer =new StringBuffer();
            String line = null;
            while ((line =  bufferedReader.readLine())!=null){
                buffer.append(line).append("\n");
            }
            txtMuti.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
