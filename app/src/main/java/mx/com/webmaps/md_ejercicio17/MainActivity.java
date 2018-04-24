package mx.com.webmaps.md_ejercicio17;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;


import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView dateText;
    AppCompatButton dateTime;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    DatePickerDialog finalPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText =(TextView) findViewById(R.id.dateTextView_id);
        dateTime = (AppCompatButton) findViewById(R.id.dateButton_id);

        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();

                int currentDate = calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentyYear = calendar.get(Calendar.YEAR);

                datePickerDialog = DatePickerDialog.newInstance(MainActivity.this, currentyYear,currentMonth,currentDate);

                datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));

                datePickerDialog.setTitle("Date Picker");

                datePickerDialog.setThemeDark(false);

                datePickerDialog.setOkText("SET");

                datePickerDialog.setCancelText("DON'T SET");

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        System.out.println("Cancel");
                    }
                });


                datePickerDialog.show(getFragmentManager(),"datePickerID");

                finalPicker = (DatePickerDialog) getFragmentManager().findFragmentByTag("datePickerID");

                if(finalPicker != null){
                    finalPicker.setOnDateSetListener(MainActivity.this);
                }


            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date= "You picked the following date: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        dateText.setText(date);
    }
}
