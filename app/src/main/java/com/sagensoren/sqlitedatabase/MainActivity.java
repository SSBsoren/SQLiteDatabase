package com.sagensoren.sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper muDB;
    EditText editTextId,editName,editEmail,editCC;
    Button buttonAdd, buttonGetData, buttonUpdate,buttonDelete,buttonViewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muDB = new DatabaseHelper(this);
        editTextId = findViewById(R.id.editText_id);
        editName = findViewById(R.id.editText_name);
        editEmail = findViewById(R.id.editText_email);
        editCC = findViewById(R.id.editText_CC);


        buttonAdd = findViewById(R.id.button_add);
        buttonDelete = findViewById(R.id.button_delete);
        buttonUpdate = findViewById(R.id.button_update);
        buttonGetData = findViewById(R.id.button_view);
        buttonViewAll = findViewById(R.id.button_viewAll);

        /*showMessage("test","testing done");*/
        AddData();
        getData();

    }

    public void AddData(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = muDB.insertData(editName.getText().toString()
                        ,editEmail.getText().toString()
                        ,editCC.getText().toString());

                if (isInserted == true){
                    Toast.makeText(MainActivity.this,"Data Inserted!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }


    public void getData(){
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editTextId.getText().toString();
                if (id.equals(String.valueOf(""))){
                    editTextId.setError("Enter ID");
                    return;
                }
                Cursor cursor = muDB.getData(id);
                String data = null;

                if (cursor.moveToNext()){
                    data = "ID:" + cursor.getString(0)+"\n"+
                            "Name:" + cursor.getString(1)+"\n"+
                            "Email:" + cursor.getString(2)+"\n"+
                            "Course Count:" + cursor.getString(3)+"\n";

                }
                showMessage("Data",data);



            }
        });
    }

    

    private void showMessage(String title, String message){

        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
