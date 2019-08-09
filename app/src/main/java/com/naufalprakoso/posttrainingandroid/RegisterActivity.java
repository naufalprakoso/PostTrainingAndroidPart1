package com.naufalprakoso.posttrainingandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtTime, edtDate;
    private RadioGroup radioGroupGender;
    private CheckBox checkBox;
    private Spinner spinnerCategory;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnSubmit = findViewById(R.id.btn_submit);
        edtName = findViewById(R.id.edt_name);
        edtTime = findViewById(R.id.edt_time);
        edtDate = findViewById(R.id.edt_birth_date);
        radioGroupGender = findViewById(R.id.radio_group_gender);
        checkBox = findViewById(R.id.check_agreement);
        spinnerCategory = findViewById(R.id.spinner_category);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.category_array,
                android.R.layout.simple_spinner_item
        );
        spinnerCategory.setAdapter(categoryAdapter);

        calendar = Calendar.getInstance();

        edtTime.setOnClickListener(this);
        edtDate.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                final String name = edtName.getText().toString();
                final boolean isGenderChecked = radioGroupGender.getCheckedRadioButtonId() == -1;
                final boolean isAgreeChecked = checkBox.isChecked();
                final String birthDate = edtDate.getText().toString();
                final String time = edtTime.getText().toString();
                final String category = spinnerCategory.getSelectedItem().toString();

                if (name.isEmpty()){
                    toast("Name must be filled!");
                }else if(isGenderChecked){
                    toast("Gender must be selected");
                }else if(!isAgreeChecked){
                    toast("You must agree");
                }else if (birthDate.isEmpty()){
                    toast("Birth date must be chosen");
                }else if(time.isEmpty()){
                    toast("Time must be chosen");
                }else if(category.equals("Choose category")){
                    toast("Category must be chosen");
                }else{
                    toast("Success");
                }

                break;
            case R.id.edt_time:
                showTimeDialog();
                break;
            case R.id.edt_birth_date:
                showDateDialog();
                break;
        }
    }

    private void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void showDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String dateFormat = "dd/MM/yy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                        edtDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },
                2018,
                1,
                1
        );

        datePickerDialog.show();
    }

    private void showTimeDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view,
                                          int hourOfDay, int minute) {
                        edtTime.setText(hourOfDay + ":" + minute);
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this)
        );

        timePickerDialog.show();
    }
}
