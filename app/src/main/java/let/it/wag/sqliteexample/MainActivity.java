package let.it.wag.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements ViewList{

    private EditText editText;
    private DBHandler dbHandler;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DBHandler(this, null, null, 2, this);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(editText.getText().toString());
            }
        });
        dbHandler.getfromDatabase();
    }

    public void addData(String data){
        if(!data.trim().equals("")){
            dbHandler = new DBHandler(this, null, null, 2, this);
            dbHandler.addProduct(data);
            editText.setText(null);
        }
    }

    @Override
    public void setList(String[] strarr) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_view, strarr);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }
}
