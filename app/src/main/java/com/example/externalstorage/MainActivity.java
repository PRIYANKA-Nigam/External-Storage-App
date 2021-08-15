package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
Button b1,b2; EditText e; TextView t;
String fileName="",filePath="",fileContent =""; @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.bt); b2=(Button)findViewById(R.id.bt2); e=(EditText)findViewById(R.id.ed); t=(TextView)findViewById(R.id.tt);
        fileName="myFile.txt"; filePath="MyDir";
        if (!isExternalStorageAvailableForRW()){ b1.setEnabled(false); }
        b1.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) { t.setText(" "); fileContent=e.getText().toString().trim();
                if (!fileContent.equals("")){ File file=new File(getExternalFilesDir(filePath),fileName);
                    FileOutputStream fileOutputStream=null;
                    try { fileOutputStream=new FileOutputStream(file); fileOutputStream.write(fileContent.getBytes());
                    } catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) { ioException.printStackTrace(); }
                    e.setText("");
                    Toast.makeText(MainActivity.this,"Information Saved to SD Card",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Text Field can not be Empty",Toast.LENGTH_SHORT).show(); } }});
        b2.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { FileReader fileReader=null;
                File file=new File(getExternalFilesDir(filePath),fileName); StringBuilder stringBuilder=new StringBuilder();
                try { fileReader=new FileReader(file); BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String line=bufferedReader.readLine(); while (line!=null){
                        stringBuilder.append(line).append('\n'); line=bufferedReader.readLine(); }
                } catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace();
                } catch (IOException ioException) { ioException.printStackTrace();
                }finally { String fileContents= "File Contents"+"\n" +stringBuilder.toString(); t.setText(fileContents); } }}); }
                private boolean isExternalStorageAvailableForRW() { String extState= Environment.getExternalStorageState();
        if (extState.equals(Environment.MEDIA_MOUNTED)){ return true; } return false; }}