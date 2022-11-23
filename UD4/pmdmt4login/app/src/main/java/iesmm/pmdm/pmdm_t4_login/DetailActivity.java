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
            TextView text=this.findViewById(R.id.welcome);
            text.setText("Bienvenido " +email);

        }


    }
}