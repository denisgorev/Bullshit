package com.example.bullshit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Message extends Activity {
    public static final String EXTRA_MESSAGE= "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Button button = (Button) findViewById(R.id.send);
        Intent intent = getIntent();
        String messageText = intent.getStringExtra(EXTRA_MESSAGE);
        EditText messageView = (EditText)findViewById(R.id.message);
        messageView.setText(messageText);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EditText messageView = (EditText)findViewById(R.id.message);
               String messageText = messageView.getText().toString(); //получение текста из объекта messageView
               Intent intent = new Intent(Intent.ACTION_SEND); //создание нового интента с опцией отправки сообшений
               intent.setType("text/plain"); //описание типа данных
               intent.putExtra(Intent.EXTRA_TEXT, messageText); //включение дополнительной информации в интент (сообщение)
               String chooserTitle = getString(R.string.chooser); //получение заголовка из Strings.xml для заголовка окна выбора приложения
               Intent chosenIntent = Intent.createChooser(intent, chooserTitle); //createChooser возвращает объект Intent со всеми заданными свойствами, но с выводом окна выбора активности
               startActivity(chosenIntent); //запуск активности, вызываемой интентом
           }
       });
    }
}
