/**
 * Mariann Freund
 * Practice 1
 * 8/16/2017
 */

package edu.gatech.seclass.tipcalulator;

import android.annotation.TargetApi;
import android.app.Activity;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import static android.icu.text.NumberFormat.getCurrencyInstance;

public class MainActivity extends Activity
        implements OnEditorActionListener, View.OnClickListener {

    //declare variables for the widget
    private EditText billAmountEditText;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView percentTV;
    private Button percentUp;
    private Button percentDown;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private TextView pPerson;
    private Button apply;
    float num = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting the spinner
        spinner = (Spinner) findViewById(R.id.split_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.split_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Get references to the widgets
        billAmountEditText = (EditText) findViewById(R.id.billAmount);
        tipTextView = (TextView) findViewById(R.id.tip_amnt);
        totalTextView = (TextView) findViewById(R.id.total_amnt);
        percentUp = (Button) findViewById(R.id.add_btn_lbl);
        percentDown = (Button) findViewById(R.id.minus_btn_lbl);
        apply = (Button) findViewById(R.id.apply_btn);
        percentTV = (TextView) findViewById(R.id.percent_value);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //set the listeners
        percentUp.setOnClickListener(this);
        percentDown.setOnClickListener(this);
        //apply.setOnClickListener(this);
        billAmountEditText.setOnClickListener(this);
        pPerson = (TextView) findViewById(R.id.per_person);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    public void addListenerOnSpinnerItemSelection(){
        spinner = (Spinner) findViewById(R.id.split_spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }


    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.split_spinner);

        apply = (Button) findViewById(R.id.apply_btn);

        apply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                calculateAndDisplay();
               /* Toast.makeText(MainActivity.this,
                        "You have selected "+ String.valueOf(spinner.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();*/

            }

        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        return false;
    }

    @TargetApi(Build.VERSION_CODES.N)


    public void calculateAndDisplay() {
        // get the bill amount
        String billAmountStr = billAmountEditText.getText().toString();
        float billAmount;
        float totalAmount = 0;
        float tipAmount;



        if (billAmountStr.equals("")) {
            billAmount = 0;

        } else {
            billAmount = Float.parseFloat(billAmountStr);
        }

        //calculate tip and total amounts
        System.out.print(num);
        float tipPercent = num / 100;
        //System.out.print(tipPercent + " tip percent");


        if (radioGroup.getCheckedRadioButtonId() == -1) {
            //no radio button is checked
            //System.out.print(" No radio selection");
        } else {
            //one of the radio button is selected
            int id = radioGroup.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) radioGroup.findViewById(id);
            String str = (String) rb.getText();

            if (str.equals("Tip")) {

                tipAmount = StrictMath.round(billAmount * tipPercent);
                totalAmount = billAmount + tipAmount;
               // System.out.print(" the tip amount is " + tipAmount);
               // System.out.print(" the total amount is " + totalAmoutn);

                NumberFormat currency = getCurrencyInstance();
                tipTextView.setText(currency.format(tipAmount));
               // System.out.print(tipAmount + " tip amount ");
                totalTextView.setText(currency.format(totalAmount));
                NumberFormat percent = NumberFormat.getPercentInstance();
                percentTV.setText(percent.format(tipPercent));



            } else if (str.equals("Total")) {

                float tipNotRounded = billAmount * tipPercent;
                totalAmount = StrictMath.round(billAmount + tipNotRounded);
                tipAmount = totalAmount - billAmount;

                NumberFormat currency = getCurrencyInstance();
                tipTextView.setText(currency.format(tipAmount));
                totalTextView.setText(currency.format(totalAmount));
                NumberFormat percent = NumberFormat.getPercentInstance();
                percentTV.setText(percent.format(tipPercent));


            } else if (str.equals("None")) {

                tipAmount = billAmount * tipPercent;
                totalAmount = billAmount + tipAmount;

                NumberFormat currency = getCurrencyInstance();
                tipTextView.setText(currency.format(tipAmount));
                totalTextView.setText(currency.format(totalAmount));
                NumberFormat percent = NumberFormat.getPercentInstance();
                percentTV.setText(percent.format(tipPercent));



            }

        }

        if(spinner.getSelectedItemPosition() == 1){
            pPerson.setText(String.valueOf(totalAmount));
        }
        else if(spinner.getSelectedItemPosition() == 2){
            pPerson.setText(String.valueOf(totalAmount /2));
        }
        else if(spinner.getSelectedItemPosition() == 3){
            pPerson.setText(String.valueOf(totalAmount /3));
        }
        else if(spinner.getSelectedItemPosition() == 4){
            pPerson.setText(String.valueOf(totalAmount /4));
        }
        else {
            pPerson.setText("none selected");


        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minus_btn_lbl:
                num = num - 1;
                if (num > 0) {
                    calculateAndDisplay();
                } else {
                    tipTextView.setText("Please tip your server!");
                }
                break;

            case R.id.add_btn_lbl:
                num = num + 1;
                if (num > 100) {
                    tipTextView.setText("You are being too generous!");
                } else {
                    calculateAndDisplay();
                    break;
                }
            case  R.id.apply_btn:
                //call the method to split the amount in the correct way
                break;
        }

    }

}
