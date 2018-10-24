package com.t3ch.shaj.android_file_data_store;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextid);
        saveButton = findViewById(R.id.saveBTNid);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();

                if (text != null) {


                    writeFile(text);


                } else {
                    Toast.makeText(getApplicationContext(), "Enter Some Data", Toast.LENGTH_SHORT).show();
                }

            }
        });

        readFile();


    }


    private void writeFile(String text) {

        try {
            FileOutputStream fileOutputStream = openFileOutput("textData.txt", Context.MODE_PRIVATE);

            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();

            Toast.makeText(getApplicationContext(), "Text Saved", Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void readFile() {

        try {
            FileInputStream fileInputStream = openFileInput("textData.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            StringBuffer stringBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line + "\n");

            }
            editText.setText(stringBuffer.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
