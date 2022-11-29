package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoExiste extends AppCompatActivity {
    EditText Usuario3;
    EditText Contrasena3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_existe);
        Usuario3 = findViewById(R.id.Usuario3);
        Contrasena3 = findViewById(R.id.Contraseña3);
    }

    public void registro2 (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"login",null,1);//cambiar nombre dependiendo el proyecto que manejes
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Us = Usuario3.getText().toString();
        String Cn = Contrasena3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("Usuario", Us);
        registro.put("Contraseña", Cn);
        // los inserto en la base de datos
        bd.insert("usuarios", null, registro);
        bd.close();
        Usuario3.setText("");
        Contrasena3.setText("");
        Toast.makeText(this, "La alta del usuario se realizo", Toast.LENGTH_SHORT).show();
    }

    public void Click3(View view){
        finish();
    }


}