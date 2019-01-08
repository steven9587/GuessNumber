package fju.com.guess;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView layoutSecret;
    private EditText layoutNumber;
    private int secret;
    private int start;
    private int finish;
    private int number;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //計算猜了幾次
        count = 0;
        //設定範圍
        start = 1;
        finish = 100;
        //產生secret
        Random r = new Random();
        secret = r.nextInt(100)+1;
        layoutSecret = findViewById(R.id.secret);
        layoutSecret.setText(secret+" ");
        layoutNumber = findViewById(R.id.number);
    }
    public void send(View view){
        number = Integer.parseInt(layoutNumber.getText().toString());
        //Secret > GuessNumner (number~finish)
        if (secret > number){
            start = number;
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Message")
                    .setMessage(start+"  to "+finish)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            layoutNumber.setText("");
                        }
                    })
                    .show();
            count++;
        }
        //Secret = GuessNumner
        if (secret == number){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Message")
                .setMessage("Yes, the number is "+secret)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        layoutNumber.setText("");
                    }
                })
                .show();
            count++;
                if (count < 4){
                Intent excellent = new Intent(this,ExcellentActivity.class);
                startActivity(excellent);
             }
            }
        //Secret < GuessNumner
        if (secret < number){
            finish = number;
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Message")
                    .setMessage(start+"  to "+finish)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            layoutNumber.setText("");
                        }
                    })
                    .show();
            count++;
        }
    }
}
