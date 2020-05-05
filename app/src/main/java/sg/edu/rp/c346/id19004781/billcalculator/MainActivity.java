package sg.edu.rp.c346.id19004781.billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmt, etPax, etDiscount;
    Button btnSplit, btnReset;
    TextView tvTotalBill, tvEachPay;
    ToggleButton toggleSvs;
    ToggleButton toggleGst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.editTextAmount);
        etPax = findViewById(R.id.editTextPax);
        etDiscount = findViewById(R.id.editTextDiscount);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        tvTotalBill = findViewById(R.id.textViewDisplayBill);
        tvEachPay = findViewById(R.id.textViewDisplayPay);
        toggleSvs = findViewById(R.id.toggleSVS);
        toggleGst = findViewById(R.id.toggleGST);


        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for the action
                //String strAmt = etAmt.getText().toString();
                //String strPax = etPax.getText().toString();
                // String strDiscount = etDiscount.getText().toString();

                double amt = 0.0;

                if (toggleSvs.isChecked()) {
                    // There is service charge of 10%
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.1;
                } else if (toggleSvs.isChecked() && toggleGst.isChecked()) {
                    // there is service charge of 10% + gst 7%
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.17;
                } else if (toggleGst.isChecked()) {
                    // There is gst 7%
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.07;
                } else {
                    amt = Double.parseDouble(etAmt.getText().toString());
                }
                tvTotalBill.setText("Total Bill: $" + String.format("%.2f", amt));

                int numPerson = Integer.parseInt(etPax.getText().toString());
                if (numPerson != 1) {
                    tvEachPay.setText("Each Pays: " + String.format("%.2f", amt / numPerson));
                } else {
                    tvEachPay.setText("Each Pays: " + amt);
                }

                //Discount
                if (etDiscount.getText().toString().trim().length() != 0) {
                    amt *= 1 - Double.parseDouble(etDiscount.getText().toString()) / 100;
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmt.setText("");
                etPax.setText("");
                toggleSvs.setChecked(false);
                toggleGst.setChecked(false);
                etDiscount.setText("");
            }
        });


    }
}
