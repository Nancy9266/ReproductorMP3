package com.example.joseandres.reproductormp3;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    //Aqui definiremos estos tres atributos para la clase MediaPlayer para administrar el archivo mp3
    //Tambien se define un entero donde se almacena la posición actual de reproducción en milisegundos
    MediaPlayer mp;
    Button btn5;
    int posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn5=(Button)findViewById(R.id.btn5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//El método destruir comprueba con un if si el objeto de la clase MediaPlayer
// está creado, si si procede a liberar recursos del mismo llamando al método release.
    public void destruir() {
        if (mp != null)
            mp.release();
    }
//El método iniciar que se ejecuta al presionar el botón "INICIAR MP3"
// primero llama al método destruir (para el caso que el mp3 este en ejecución actualmente).
// seguidamente creamos un objeto de la clase MediaPlayer llamando al método create
// (en este hacemos referencia al archivo que esta en la carpeta raw), Llamamos al método
// start.Por último extraemos el texto del quinto botón y verificamos
// si la reproducción debe ejecutarse en forma indefinida una y otra vez.
    public void iniciar(View v) {
        destruir();
        mp = MediaPlayer.create(this, R.raw.cancion);
        mp.start();
        String op = btn5.getText().toString();
        if (op.equals("No reproducir MP3"))
            mp.setLooping(false);
        else
            mp.setLooping(true);
    }
//El método pausar verifica que el objeto de la clase MediaPlayer este creado y
// en ejecución, en caso afirmativo recuperamos la posición actual de reproducción
// y llamamos seguidamente al método pause.
    public void pausar(View v) {
        if (mp != null && mp.isPlaying()) {
            posicion = mp.getCurrentPosition();
            mp.pause();
        }
    }
//El método continuar comprueba que el objeto de la clase MediaPlayer este creado y
// la propiedad isPlaying retorne a false para proceder a posicionar en que milisegundo
// debe continuar la reproducción.
    public void continuar(View v) {
        if (mp != null && mp.isPlaying() == false) {
            mp.seekTo(posicion);
            mp.start();
        }
    }
//El método detener interrumpe la ejecución del mp3 e inicializa el atributo posicion con cero.
    public void detener(View v) {
        if (mp != null) {
            mp.stop();
            posicion = 0;
        }
    }
//Cuando se presiona el botón que cambia si la reproducción se efectúa en forma circular o no,
// lo que sigue es extraer su texto para almacenar el valor opuesto.
    public void reproductor(View v) {
        detener(null);
        String op = btn5.getText().toString();
        if (op.equals("No reproducir MP3"))
            btn5.setText("Reproducir MP3");
        else
            btn5.setText("No reproducir MP3");
    }
}
