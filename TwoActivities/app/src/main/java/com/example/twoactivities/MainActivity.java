package com.example.twoactivities;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    Button btn;
    //EditText et;
    TextView tv;

    public static String REQUEST_MESSAGE = "Request_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        tv = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tv.setText("Prva igra");

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(REQUEST_MESSAGE, tv.getText().toString());
                activity2Launcher.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> activity2Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        String response = data.getStringExtra(MainActivity2.RESPONSE_MESSAGE);
                        tv.setText(response);
                    }
                }
            }
    );

}



