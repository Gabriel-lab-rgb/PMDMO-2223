package iesmm.pmdm.pmdm_t4_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //recuperar datos del bundle transmitido
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            String email=bundle.getString("email");
            String user=bundle.getString("usuario");
            String tel=bundle.getString("telefono");
            TextView textUser=this.findViewById(R.id.username);
            textUser.setText(textUser.getText() + user);
            TextView textEmail=this.findViewById(R.id.email);
            textEmail.setText(textEmail.getText() +email);
            TextView textTel=this.findViewById(R.id.telefono);
            textTel.setText(textTel.getText() +tel);

        }


    }


}