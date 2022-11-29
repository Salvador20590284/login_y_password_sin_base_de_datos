package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Aceptado extends AppCompatActivity {
    TextView Usu2;
    TextView Contra;
    EditText Nueva;
    EditText ant;
    Bundle datos;
    Bundle datos2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceptado);
        Usu2 = findViewById(R.id.Usuario2);
        datos = getIntent().getExtras();
        Usu2.setText(""+datos.getString("Usuario"));
        Contra = findViewById(R.id.Contraseña2);
        datos2 = getIntent().getExtras();
        Contra.setText(""+datos2.getString("Contra"));
        Nueva = findViewById(R.id.Nueva);
        ant = findViewById(R.id.Contraant);
    }

    // Método para modificar la información del usuario
    public void modificacion(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "login", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Us = ant.getText().toString();
        String Cn = Nueva.getText().toString();
        ContentValues registro = new ContentValues();
        // actualizamos con los nuevos datos, la información cambiada
        registro.put("Usuario", Us);
        registro.put("Contraseña", Cn);
        int cant = bd.update("usuarios", registro, "Contraseña=" + Cn, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Datos modificados con éxito", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe usuario", Toast.LENGTH_SHORT).show();
    }

    public void click2 (View view){

        finish();
    }
}