package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Usuario;
    EditText Contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Usuario = findViewById(R.id.Usuario);
        Contrasena = findViewById(R.id.Contraseña);

    }

    public void registro (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"login",null,1);//cambiar nombre dependiendo el proyecto que manejes
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Us = Usuario.getText().toString();
        String Cn = Contrasena.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("Usuario", Us);
        registro.put("Contraseña", Cn);
        // los inserto en la base de datos
        bd.insert("usuarios", null, registro);
        bd.close();
        Usuario.setText("");
        Contrasena.setText("");
        Toast.makeText(this, "La alta del usuario se realizo", Toast.LENGTH_SHORT).show();
    }
    // Hacemos búsqueda de usuario por DNI
    public void inicio (View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"login", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Cn = Contrasena.getText().toString();
        Cursor fila = bd.rawQuery( "select Usuario from usuarios where Contraseña = " + Cn, null);
        if (fila.moveToFirst()) {
            Usuario.setText(fila.getString(0));
            Intent a = new Intent(this, Aceptado.class);
            a.putExtra("Usuario", Usuario.getText().toString());
            a.putExtra("Contra", Contrasena.getText().toString());
            startActivity(a);
        }else{
            Toast.makeText(this, "No existe ningún usuarios con esa contraseña", Toast.LENGTH_SHORT).show();
            Intent f = new Intent(this, NoExiste.class);
            f.putExtra("Usuario", Usuario.getText().toString());
            f.putExtra("Contra", Contrasena.getText().toString());
            startActivity(f);
            bd.close();
        }
    }

}