package iesmm.pmdm.tres_en_raya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private JuegoTresEnRaya mJuego;

    private Button mBotonesTablero[];

    private TextView mInfoTexto;

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

    private void comenzarJuego(){
        mJuego.limpiarTablero();

        for(int i=0;i<mBotonesTablero.length;i++){
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

        if(jugador==JuegoTresEnRaya.JUGADOR)
            mBotonesTablero[casilla].setTextColor(Color.rgb(0,200,0));
        else
            mBotonesTablero[casilla].setTextColor(Color.rgb(200,0,0));


        int estadoJuego=comprobarEstadoJuego();

        if(estadoJuego==1||estadoJuego==2)
            gameOver();
        else if(estadoJuego==0){
            if(jugador==JuegoTresEnRaya.JUGADOR)
                mTurno=JuegoTresEnRaya.MAQUINA;
            else if(jugador==JuegoTresEnRaya.JUGADOR);

            controlarTurno();
        }
    }

    private int comprobarEstadoJuego(){
        int estado=mJuego.comprobarGanador();
        if(estado==1)
            mInfoTexto.setText(R.string.result_human_wins);
        else if(estado==2)
            mInfoTexto.setText(R.string.result_computer_wins);
        return estado;
    }

    private void gameOver(){

        gameOver=true;

        for(int i=0;i<mBotonesTablero.length;i++){
            mBotonesTablero[i].setEnabled(false);
        }

    }
    private void onClick(View boton){

        int id=boton.getId();
        String descripcionBoton=((Button) findViewById(id)).getContentDescription().toString();
        int casilla =Integer.parseInt(descripcionBoton) -1;

        if(mBotonesTablero[casilla].isEnabled()){

            colocarFichaEnTablero(JuegoTresEnRaya.JUGADOR,casilla);
        }

    }




}

