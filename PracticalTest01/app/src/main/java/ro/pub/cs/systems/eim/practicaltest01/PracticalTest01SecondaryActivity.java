package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private class BtnListener implements View.OnClickListener {

        public void onClick(View view) {
            Button pressed = (Button)view;

            if (pressed.getId() == R.id.ok_button) {
                setResult(1);
                finish();
            }
            else if (pressed.getId() == R.id.cancel_button) {
                setResult(0);
                finish();
            }
        }
    }

    BtnListener listener = new BtnListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);
        TextView sum_text = (TextView)findViewById(R.id.sum_text);
        Intent intent = getIntent();
        Button okbtn = (Button)findViewById(R.id.ok_button);
        okbtn.setOnClickListener(listener);
        Button cancel_button = (Button)findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(listener);

        if (intent != null) {
            String value = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01.secondActivity.SUM");
            sum_text.setText(value.toString());
        }
    }
}
