package com.example.anu.to_do_list;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button save_button;
    private EditText enter_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save_button = (Button) findViewById(R.id.button);
        enter_details = (EditText) findViewById(R.id.editText);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!enter_details.getText().toString().equals("")){
                String message = enter_details.getText().toString();
                write(message);
            }else{
                    //nothing
                }
            }
        });
        try {
            if(read() != null){
                enter_details.setText(read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(String message){
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput("ToDoList.txt", Context.MODE_PRIVATE));
            writer.write(message);
            writer.close(); // always close streams

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String read() throws IOException{
        String result ="";
        InputStream inputStream = openFileInput("ToDoList.txt");

        if(inputStream != null){
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String temString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((temString = bufferedReader.readLine())!= null){
                stringBuilder.append(temString);
                stringBuilder.append(System.getProperty("line.separator"));
            }

            inputStream.close();
            result = stringBuilder.toString();
        }
        return result;
    }
}
