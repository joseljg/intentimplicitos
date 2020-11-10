package es.joseljg.intentimplcitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sitioWeb;
    EditText sitioLocalizacion;
    EditText textoCompartido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--------------------------------------
        sitioWeb = findViewById(R.id.edt_url);
        sitioLocalizacion = findViewById(R.id.edt_localizacion);
        textoCompartido = findViewById(R.id.edt_texto_compartido);
    }

    public void abrirSitioWeb(View view)
    {
        Uri direccionURL = Uri.parse(sitioWeb.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, direccionURL);
        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"no es posible abrir la url con ninguna aplicacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirLocalizacion(View view)
    {
        Uri direccionLocalizacion = Uri.parse("geo:0,0?q="+sitioLocalizacion.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, direccionLocalizacion);
        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"no es posible abrir la url con ninguna aplicacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void compartirTexto(View view)
    {
        String txt = textoCompartido.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.txt_textocompartido)
                .setText(txt)
                .startChooser();
    }
}