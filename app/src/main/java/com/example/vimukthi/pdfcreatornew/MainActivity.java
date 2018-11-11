package com.example.vimukthi.pdfcreatornew;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;


public class MainActivity extends Activity {

    Button btnCreatePDF;
    EditText editText;
  //  String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreatePDF =(Button)findViewById(R.id.btnCreatePDF);
        btnCreatePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cretePDF(view);
            }
        });

    }
    public void cretePDF(View view){
        String path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF Creator";

        File dir =new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        Date date = new Date();
        CharSequence sequence  = DateFormat.format("MM-dd-yy hh-mm-ss", date.getTime());
        Log.d("PDFCretor","PDF path" +path);
        File file =new File(dir ,sequence.toString()+".pdf");

        editText=(EditText)findViewById(R.id.edtText);
        Document doc=new Document() ;

        try {
            PdfWriter.getInstance(doc ,new FileOutputStream(file));
            doc.open();
            doc.add(new Paragraph(editText.getText().toString()));
            doc.close();
            Toast.makeText(MainActivity.this,"PDF creted",Toast.LENGTH_SHORT).show();
        } catch (DocumentException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Documentation not created",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"File not creted",Toast.LENGTH_SHORT).show();
        }


    }
}
