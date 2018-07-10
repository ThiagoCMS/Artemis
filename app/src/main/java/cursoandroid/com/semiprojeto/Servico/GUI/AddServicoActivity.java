package cursoandroid.com.semiprojeto.Servico.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import cursoandroid.com.semiprojeto.R;

public class AddServicoActivity extends AppCompatActivity {
    private Button button;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_servico);

        button = findViewById(R.id.button);
        list = findViewById(R.id.list);

    }
}
