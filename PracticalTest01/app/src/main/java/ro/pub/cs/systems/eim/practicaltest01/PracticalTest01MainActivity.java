package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import general.Constants;


public class PracticalTest01MainActivity extends Activity {


    private class MyButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TextView left_text = (TextView)findViewById(R.id.text_left);
            TextView right_text = (TextView)findViewById(R.id.text_right);


            if(((Button)view).getId() == R.id.button_left) {
                int new_value = Integer.parseInt(left_text.getText().toString()) + 1;
                left_text.setText(Integer.toString(new_value));
            }
            else if (((Button)view).getId() == R.id.button_right) {
                int new_value = Integer.parseInt(right_text.getText().toString()) + 1;
                right_text.setText(Integer.toString(new_value));
            }
            else if (((Button)view).getId() == R.id.button_intent) {
                Log.d(Constants.DTAG, "Gonna start the intent");
//                Intent intent = new Intent("ro.pub.cs.systems.eim.practicaltest01.intent.action.secondActivity");
                Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                int left_value = Integer.parseInt(right_text.getText().toString());
                int right_value = Integer.parseInt(left_text.getText().toString());
                intent.putExtra("ro.pub.cs.systems.eim.practicaltest01.secondActivity.SUM", Integer.toString(left_value + right_value));
                startActivityForResult(intent, Constants.SECOND_REQ_CODE);
            }

        }
    }

    private MyButtonListener listener = new MyButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practical_test01_main);
        Button left = (Button)findViewById(R.id.button_left);
        Button right = (Button)findViewById(R.id.button_right);
        left.setOnClickListener(listener);
        right.setOnClickListener(listener);
        Button bintent = (Button)findViewById(R.id.button_intent);
        bintent.setOnClickListener(listener);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.LEFT_TAG)) {
            Log.d(Constants.DTAG, "Restore LEFT");
            ((TextView)findViewById(R.id.text_left)).setText(savedInstanceState.getString(Constants.LEFT_TAG));
        }
        if (savedInstanceState.containsKey(Constants.RIGHT_TAG)) {
            Log.d(Constants.DTAG, "Restore RIGHT");
            ((TextView)findViewById(R.id.text_right)).setText(savedInstanceState.getString(Constants.RIGHT_TAG));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.LEFT_TAG, ((TextView)findViewById(R.id.text_left)).getText().toString());
        savedInstanceState.putString(Constants.RIGHT_TAG, ((TextView)findViewById(R.id.text_right)).getText().toString());
        Log.d(Constants.DTAG, "Saved values");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d(Constants.DTAG, "Comes back");
        if (requestCode == Constants.SECOND_REQ_CODE) {
            Toast.makeText(this, new String("Came back with value: " + resultCode), Toast.LENGTH_LONG).show();
        }
    }
}
