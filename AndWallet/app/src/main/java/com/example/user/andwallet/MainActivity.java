package com.example.user.andwallet;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.typeText)
    EditText typeText;
    @Bind(R.id.amountText)
    EditText amountText;
    @Bind(R.id.layoutContainer)
    LinearLayout layoutContainer;
    TextView summaryText;
    boolean ifClicked = false;
    int incomeSum = 0;
    int expenseSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.saveButton)
    public void todoSaveClicked(Button button) {
        LayoutInflater inflater = getLayoutInflater();

        View moneyRow = inflater.inflate(R.layout.money_row,
                layoutContainer,
                false);
        TextView moneyList = (TextView) moneyRow.findViewById(R.id.moneyList);
        ImageView moneyImage = (ImageView) moneyRow.findViewById(R.id.moneyImage);
        TextView moneyAmount = (TextView) moneyRow.findViewById(R.id.moneyAmount);

        if (typeText.getText().toString().isEmpty()) {
            showSnackbarMessage(getString(R.string.invalidInputText));
        } else if (amountText.getText().toString().isEmpty()) {
            showSnackbarMessage(getString(R.string.amountText));
        } else if (!(amountText.getText().toString().isEmpty())) {
            moneyList.setText(typeText.getText().toString());
            moneyAmount.setText(amountText.getText().toString());
            if (ifClicked) {
                moneyImage.setImageResource(R.drawable.income);
                moneyAmount.setTextColor(Color.GREEN);
                incomeSum+=Integer.parseInt(amountText.getText().toString());
                System.out.println(incomeSum);
            } else if (!ifClicked) {
                moneyImage.setImageResource(R.drawable.expense);
                moneyAmount.setTextColor(Color.RED);
                expenseSum+=Integer.parseInt(amountText.getText().toString());
                System.out.println(expenseSum);
            }
        }
            layoutContainer.addView(moneyRow);
            typeText.setText("");
            amountText.setText("");

            int summary=incomeSum-expenseSum;
            //String summaryInfo=getString(R.string.summaryString);
            //summaryInfo=String.format(summaryInfo,summary);
            TextView summaryText=(TextView) findViewById(R.id.summaryText);
            summaryText.setText(getResources().getString(R.string.summaryString,summary));
        }

        @OnClick(R.id.typeToggle)
        public void typeClicked (Button button){
            if (!ifClicked) {
                ifClicked = true;
            } else {
                ifClicked = false;
            }
        }

        @OnClick(R.id.deleteAll)
        public void btnDeleteAll (Button btn){
            layoutContainer.removeAllViews();
        }

    public void showSnackbarMessage(String msg) {
        Snackbar.make(layoutContainer, msg, Snackbar.LENGTH_LONG).show();
    }

}
