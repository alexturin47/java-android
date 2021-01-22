package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

      TextView textCalc;
      TextView valueText;
      String firstValue = ""; // первое введенное число операции
      String command = "=";   // команда операции над числами
      boolean stop = false;   // флаг запрета на ввод

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //описываем тестовые поля и кнопки калькулятора
        textCalc = (TextView)findViewById(R.id.textCalc);
        valueText = (TextView)findViewById(R.id.valueText);
        Button btnClear=(Button)findViewById(R.id.btnClear);
        Button btnNum1=(Button)findViewById(R.id.btnNum1);
        Button btnNum2=(Button)findViewById(R.id.btnNum2);
        Button btnNum3=(Button)findViewById(R.id.btnNum3);
        Button btnNum4=(Button)findViewById(R.id.btnNum4);
        Button btnNum5=(Button)findViewById(R.id.btnNum5);
        Button btnNum6=(Button)findViewById(R.id.btnNum6);
        Button btnNum7=(Button)findViewById(R.id.btnNum7);
        Button btnNum8=(Button)findViewById(R.id.btnNum8);
        Button btnNum9=(Button)findViewById(R.id.btnNum9);
        Button btnNum0=(Button)findViewById(R.id.btnNum0);
        Button btnComma=(Button)findViewById(R.id.btnComma);
        Button btnDiv=(Button)findViewById(R.id.btnDiv);
        Button btnMulti=(Button)findViewById(R.id.btnMulti);
        Button btnDeg=(Button)findViewById(R.id.btnDeg);
        Button btnInc=(Button)findViewById(R.id.btnInc);
        Button btnRemDiv=(Button)findViewById(R.id.btnRemDiv);
        Button btnCalc=(Button)findViewById(R.id.btnCalc);

        // устанавливаем что обрабытвать события OnClick() компонентов будет Activity
        btnClear.setOnClickListener(this);
        btnNum1.setOnClickListener(this);
        btnNum2.setOnClickListener(this);
        btnNum3.setOnClickListener(this);
        btnNum4.setOnClickListener(this);
        btnNum5.setOnClickListener(this);
        btnNum6.setOnClickListener(this);
        btnNum7.setOnClickListener(this);
        btnNum8.setOnClickListener(this);
        btnNum9.setOnClickListener(this);
        btnNum0.setOnClickListener(this);
        btnComma.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDeg.setOnClickListener(this);
        btnInc.setOnClickListener(this);
        btnRemDiv.setOnClickListener(this);
        btnCalc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { // обработка нажатий кнопок
        switch (v.getId()) {
            case R.id.btnClear:  // нажата кнопка сброса
                textCalc.setText("");   // очищаем поля ввода
                valueText.setText("0");
                firstValue = "";        // и переменные
                break;
            case R.id.btnInc:  // кнопка +
                setCommand("+");
                break;
            case R.id.btnDeg: // кнопка -
                setCommand("-");
                break;
            case R.id.btnDiv:  // кнопка /
                setCommand("/");
                break;
            case R.id.btnRemDiv: // кнопка % - деление с остатком
                setCommand("%");
                break;
            case R.id.btnMulti: // кнопка *
                setCommand("*");
                break;
            case R.id.btnComma: // кнопка . - десятичный разделитель
                //проверяем что число не содеджит уже десятичного разделителя
                if (!valueText.getText().toString().contains(".")) valueText.setText(String.format("%s.", valueText.getText()));
                break;
            case R.id.btnCalc: // кнопка =
                // выводим выичслемое выражение в TextView
                textCalc.setText(String.format("%s%s", textCalc.getText().toString(), valueText.getText()));
                // преобразуем строки в числа типа float и передаем на вычисление
                Calc(Float.parseFloat(firstValue),Float.parseFloat(valueText.getText().toString()));
                break;
            default: Button b = (Button) findViewById(v.getId());  // обработка цифровых кнопок
                if (stop) {                 // если запрет на ввод
                    valueText.setText("");  // стираем прежнее значение
                    stop = false;           // и разрешаем ввод
                }
                //если в текстовом поле только 0, вместо него записываем цифру нажатой кнопки
                if (valueText.getText().toString().length() == 1 && valueText.getText().toString().substring(0,1).equals("0")) {
                    valueText.setText(b.getText());
                    // иначе добавляем в конец числа цифру с нажатой кнопки
                } else valueText.setText(String.format("%s%s", valueText.getText(), b.getText()));
                break;
        }
    }

    private void setCommand(String cmd) {
        firstValue = valueText.getText().toString();    //записываем первое число выражения из тестовго поля в переменную
        textCalc.setText(String.format("%s%s", firstValue, cmd)); // выводим выражение
        valueText.setText("0");                         //сбрасываем поле ввода числа
        command = cmd;                                  //устаналиваем команду операции
    }


    private void Calc(float a, float b) { // вычисление выражения
        float res = 0;                   // результат 0
        switch (command) {                // определяем команду операции
            case "=":                     // и выполняем ее над числами
                res = b;
                break;
            case "*":
                res = a * b;
                break;
            case "/":
                if (b == 0) {           // проверка деления на 0
                    textCalc.setText("ошибка деления на 0");
                    command = "=";
                    return;
                }
                res = a / b;
            break;
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "%":
                if (b == 0) {
                    textCalc.setText("ошибка деления на 0");
                    command = "=";
                    return;
                }
                res = a % b;
                break;
        }
        //выводим результат в поле вычисления и поле ввода
        textCalc.setText(String.format("%s=%s", textCalc.getText(), String.valueOf(res)));
        valueText.setText(String.valueOf(res));
        command = "=";  // сбрасываем на команду по умолчанию
        stop = true;    // заперщаем ввод в поле ввода пока там отображается результат
    }
}