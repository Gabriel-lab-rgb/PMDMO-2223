package iesmm.pmdm.tres_en_raya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mJugadorMediaPlayer;
    private MediaPlayer mBackgroundMusicPlayer;
    private MediaPlayer mVentanaMediaPlayer;


    private JuegoTresEnRaya mJuego;

    private Button mBotonesTablero[];

    private TextView mInfoTexto;

    private int maquina=0;
    private int jugador=0;
    private int npartidas=0;

    private char mTurno= JuegoTresEnRaya.JUGADOR;

    private boolean gameOver =false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // EJECUCIÓN INICIAL DE LA LÓGICA DEL VIDEOJUEGO


        mBotonesTablero= new Button[JuegoTresEnRaya.DIM_TABLERO];
        mBotonesTablero[0]=(Button) findViewById(R.id.one);
        mBotonesTablero[1]=(Button) findViewById(R.id.two);
        mBotonesTablero[2]=(Button) findViewById(R.id.three);
        mBotonesTablero[3]=(Button) findViewById(R.id.four);
        mBotonesTablero[4]=(Button) findViewById(R.id.five);
        mBotonesTablero[5]=(Button) findViewById(R.id.six);
        mBotonesTablero[6]=(Button) findViewById(R.id.seven);
        mBotonesTablero[7]=(Button) findViewById(R.id.eight);
        mBotonesTablero[8]=(Button) findViewById(R.id.nine);

        mInfoTexto=(TextView) findViewById(R.id.information);

        mJuego =new JuegoTresEnRaya();
        comenzarJuego();
    }

    protected void onResume(){
        super.onResume();
        mJugadorMediaPlayer=MediaPlayer.create(this,R.raw.splatoon_spinner);
        mBackgroundMusicPlayer=MediaPlayer.create(this,R.raw.splattack);
        mVentanaMediaPlayer=MediaPlayer.create(this,R.raw.splatoon_alert);


        mBackgroundMusicPlayer.setLooping(true);
        mBackgroundMusicPlayer.start();
    }

    protected void onPause(){
        super.onPause();
        mJugadorMediaPlayer.release();
        mBackgroundMusicPlayer.release();
        mVentanaMediaPlayer.release();
    }

    private void comenzarJuego(){
        mJuego.limpiarTablero();

        for(int i=0;i<mBotonesTablero.length;i++){
            mBotonesTablero[i].setBackgroundResource(R.drawable.interrogacion);
            mBotonesTablero[i].setText("");
            mBotonesTablero[i].setEnabled(true);
        }

        controlarTurno();
    }

    private void controlarTurno(){

        if(mTurno==JuegoTresEnRaya.JUGADOR)
            mInfoTexto.setText(R.string.primero_jugador);
        else if (mTurno==JuegoTresEnRaya.MAQUINA){
            int casilla =mJuego.getMovimientoMaquina();

            colocarFichaEnTablero(JuegoTresEnRaya.MAQUINA,casilla);

            if(!gameOver){
                mTurno=JuegoTresEnRaya.JUGADOR;
                mInfoTexto.setText(R.string.turno_jugador);
            }
        }
    }

    private void colocarFichaEnTablero(char jugador, int casilla){

        mJuego.moverFicha(jugador,casilla);

        mBotonesTablero[casilla].setEnabled(false);
        mBotonesTablero[casilla].setText(String.valueOf(jugador));

        if(jugador==JuegoTresEnRaya.JUGADOR){
            //mBotonesTablero[casilla].setTextColor(Color.rgb(0,200,0));
            mBotonesTablero[casilla].setBackgroundResource(R.drawable.icons48);
           mJugadorMediaPlayer.start();
        }
        else
            mBotonesTablero[casilla].setBackgroundResource(R.drawable.circle);
            //mBotonesTablero[casilla].setTextColor(Color.rgb(200,0,0));

        int estadoJuego=comprobarEstadoJuego();

        if(estadoJuego==1||estadoJuego==2)
                gameOver();


        else if(estadoJuego==0){
       if(jugador==JuegoTresEnRaya.JUGADOR)
           mTurno=JuegoTresEnRaya.MAQUINA;
       else if(jugador==JuegoTresEnRaya.MAQUINA)
           mTurno=JuegoTresEnRaya.JUGADOR;

            controlarTurno();
        }
    }

    private int comprobarEstadoJuego() {
        int estado = mJuego.comprobarGanador();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (estado == 1) {
            jugador=jugador+1;
            npartidas=npartidas+1;
            mInfoTexto.setText(R.string.result_human_wins);
            TextView valor=(TextView) findViewById(R.id.player_score);
            valor.setText(String.valueOf(jugador));
            TextView partidas=(TextView) findViewById(R.id.tie_score);
            partidas.setText(String.valueOf(npartidas));

            builder.setTitle("¡Felicidades!");
            builder.setMessage("Has ganado a la máquina");
            builder.setPositiveButton("Aceptar", null);

            AlertDialog dialog = builder.create();
            dialog.show();
            mVentanaMediaPlayer.start();
        } else if (estado == 2){
            maquina=maquina+1;
            npartidas=npartidas+1;
            mInfoTexto.setText(R.string.result_computer_wins);
            TextView valor=(TextView) findViewById(R.id.computer_score);
            valor.setText(String.valueOf(maquina));
            TextView partidas=(TextView) findViewById(R.id.tie_score);
            partidas.setText(String.valueOf(npartidas));

            builder.setTitle("¡Lastima!");
            builder.setMessage("Has perdido contra la máquina");
            builder.setPositiveButton("Aceptar", null);

            AlertDialog dialog = builder.create();
            dialog.show();
            mVentanaMediaPlayer.start();
    };

        return estado;
    }

    private void gameOver()  {

        gameOver=true;

        for(int i=0;i<mBotonesTablero.length;i++){
            mBotonesTablero[i].setEnabled(false);
        }

        comenzarJuego();

    }
    public void onClick(View boton){

        int id=boton.getId();
        String descripcionBoton=((Button) findViewById(id)).getContentDescription().toString();
        int casilla =Integer.parseInt(descripcionBoton) -1;

        if(mBotonesTablero[casilla].isEnabled()){

            colocarFichaEnTablero(JuegoTresEnRaya.JUGADOR,casilla);
        }

    }

    public void newGame(View boton){

        maquina=0;
        TextView scoreMaquina=(TextView) findViewById(R.id.computer_score);
        scoreMaquina.setText(String.valueOf(maquina));
        jugador=0;
        TextView scoreJugador=(TextView) findViewById(R.id.player_score);
        scoreJugador.setText(String.valueOf(maquina));
        npartidas=0;
        TextView partidas=(TextView) findViewById(R.id.tie_score);
        partidas.setText(String.valueOf(npartidas));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Nueva partida!");
        builder.setMessage("Has iniciado una nueva partida");
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
        mVentanaMediaPlayer.start();

        comenzarJuego();


    }






}

