package com.example.bullshit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button1;
    ImageView imageView;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent intentTimer = new Intent(this, Timer.class);
                startActivity(intentTimer);
                break;

            case R.id.action_item1:
                Toast.makeText(MainActivity.this, getString(R.string.action_item1), Toast.LENGTH_LONG).show();
                break;

            case R.id.action_item2:
                Toast.makeText(MainActivity.this, getString(R.string.action_item2), Toast.LENGTH_LONG).show();
                break;

            case R.id.action_item3:
                Toast.makeText(MainActivity.this, getString(R.string.action_item3), Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);

    }

   public int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAlive();
    }
     public void setAlive(){
        textView = (TextView) findViewById(R.id.textView);
        ImageButton button2 = (ImageButton) findViewById(R.id.imageButton);
        button1 = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView1);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = textView.getText().toString();
                Intent intent = new Intent(MainActivity.this, Message.class);
                intent.putExtra(Message.EXTRA_MESSAGE, messageText);
                startActivity(intent);
            }
        });

        final String[] words = getResources().getStringArray(R.array.adjectives_m);
        final String[] words2 = getResources().getStringArray(R.array.nouns_m);
        final String[] words3 = getResources().getStringArray(R.array.adjectives_f);
        final String[] words4 = getResources().getStringArray(R.array.nouns_f);
        final int in_adj_m[] = new int[words.length];
        final int in_noun_m[] = new int[words2.length];
        final int in_adj_f[] = new int[words3.length];
        final int in_noun_f[] = new int[words4.length];
        final Random rnd = new Random();
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int ran = rnd.nextInt(2);

                if (ran == 0) {
                    for (int i = 0; i < words.length-1; i++) {
                        in_adj_m[i] = i;
                    }
                    for (int i = 0; i < words2.length-1; i++) {
                        in_noun_m[i] = i;
                    }
                    int a = getRandom(in_adj_m);
                    int b = getRandom(in_noun_m);
                    textView.setText(String.format("%s %s", words[a], words2[b]));
                }
                else {
                    for (int i = 0; i < words3.length; i++) {
                        in_adj_f[i] = i;
                    }
                    for (int i = 0; i < words4.length; i++) {
                        in_noun_f[i] = i;
                    }
                    int a = getRandom(in_adj_f);
                    int b = getRandom(in_noun_f);
                    textView.setText(String.format("%s %s", words3[a], words4[b]));
                }
            }
        });

        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Вы хотите закрыть приложение?")
                                .setCancelable(false)
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Закрытие приложения");
                        alert.show();

                    }
                }
        );

    }
}
