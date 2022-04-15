package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    TextView finalAnsText;
    TextView resultText;
    TextView selectedText;
    Button calBtn;
    Button lotteryBtn;
    int finalAns;
    List<Integer> prizeList;
    List<Integer> selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
    }

    private void initial() {
        inputText = findViewById(R.id.inputText);
        calBtn = findViewById(R.id.calBtn);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputText.getText().toString().isEmpty()) {
                    return;
                }
                String value = inputText.getText().toString();
                int finalValue = Integer.parseInt(value);
                finalAns = calculateNumber(finalValue);
                finalAnsText.setText(String.valueOf(finalAns));
            }
        });

        finalAnsText = findViewById(R.id.finalAnsText);
        lotteryBtn = findViewById(R.id.lotteryBtn);
        resultText = findViewById(R.id.resultText);
        selectedText = findViewById(R.id.selectedText);

        // 初始化抽獎選項
        prizeList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 4, 5, 5, 5, 5));
        selectedList = new ArrayList<>();

        lotteryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prizeList.size() == 0) {
                    lotteryBtn.setClickable(false);
                    lotteryBtn.setText("抽獎已結束");
                    lotteryBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                }
                resultText.setText(lotteryGame(prizeList));
                selectedText.setText(selectedList.toString());
            }
        });
    }

    private int calculateNumber(int number) {
        int ans = 0;
        if (number % 2 == 0) {
            ans = -(number / 2);
        } else {
            ans = -(number - 1) / 2 + number;
        }
        return ans;
    }

    private String lotteryGame(List<Integer> prizeList) {
        int index = (int) Math.floor(Math.random() * (prizeList.size() - 1));
        if (prizeList.size() > 0) {
            selectedList.add(prizeList.get(index));
            prizeList.remove(index);
        } else {
            return "獎項已全數抽完";
        }
        return String.valueOf(selectedList.get(selectedList.size() - 1));
    }
}