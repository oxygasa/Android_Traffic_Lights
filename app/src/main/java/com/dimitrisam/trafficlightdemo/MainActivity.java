package com.dimitrisam.trafficlightdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout b_1, b_2, b_3; //3 лампочки светофора
    private Button button_1; //Start кнопка
    private boolean startStopFlag = false;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Что делать при первом запуске приложения
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Активити мэйн
        b_1 = findViewById(R.id.bulb_1); //ID каждой лампочки можно подсмотреть в activity_main.xml
        b_2 = findViewById(R.id.bulb_2);
        b_3 = findViewById(R.id.bulb_3);
        button_1 = findViewById(R.id.button1); //ID кнопки
    }

    public void onClickStart(View view) { //Кнопка старт запустит светофор
        //Новый поток происходит независимо от мэйна и не замораживает программу
//Кнопки мэйна и поля будут работать по прежнему.
        if (!startStopFlag) { //if написан для того, чтобы не создавать потоки раз за разом.
            button_1.setText("Stop");
            startStopFlag = true;
            new Thread(this::run).start();
        } else {
            button_1.setText("Start");
            startStopFlag = false;
        }
    }

    @Override
    protected void onDestroy() { //При выходе из приложения кнопкой "Назад".
        super.onDestroy();
        startStopFlag = false; //Останавливаю светофор, чтобы он не моргал в фоне.
    }

    private void run() {
        while (startStopFlag) { //Светофор сверкает бесконечно при нажатии "Старт" кнопки. И гаснет на "Стоп" кнопке.
            counter++;

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (counter) {
                case 1:
                    b_1.setBackgroundColor(getResources().getColor(R.color.red));
                    b_2.setBackgroundColor(getResources().getColor(R.color.grey));
                    b_3.setBackgroundColor(getResources().getColor(R.color.grey));
                    break;
                case 2:
                    b_1.setBackgroundColor(getResources().getColor(R.color.red));
                    b_2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    b_3.setBackgroundColor(getResources().getColor(R.color.grey));
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    b_1.setBackgroundColor(getResources().getColor(R.color.grey));
                    b_2.setBackgroundColor(getResources().getColor(R.color.grey));
                    b_3.setBackgroundColor(getResources().getColor(R.color.green));
                    break;
                case 9:
                    b_1.setBackgroundColor(getResources().getColor(R.color.grey));
                    b_2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    b_3.setBackgroundColor(getResources().getColor(R.color.grey));
                    break;
                case 10:
                    b_1.setBackgroundColor(getResources().getColor(R.color.red));
                    b_2.setBackgroundColor(getResources().getColor(R.color.grey));
                    b_3.setBackgroundColor(getResources().getColor(R.color.grey));
                    counter = 1;
                    break;


            }

        }
    }
}