package com.example.relifems.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.relifems.R;
import com.example.relifems.sqlite.SQLiteHelper;
import com.example.relifems.home.DashboardActivity;
import com.example.relifems.utils.StaticMethod;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button LogInButton, RegisterButton;
    private EditText Mobile, Password;
    private String MobileHolder, PasswordHolder;
    private Boolean EditTextEmptyHolder;
    private SQLiteDatabase sqLiteDatabaseObj;
    private SQLiteHelper sqLiteHelper;
    private Cursor cursor;
    private String TempPassword = "NOT_FOUND";
    private String UserName;
    public static final String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bindViews();
    }

    public void bindViews() {

        LogInButton = findViewById(R.id.bt_Login);
        RegisterButton = findViewById(R.id.btn_SignUp);
        Mobile = findViewById(R.id.et_MobileNumber);
        Password = findViewById(R.id.et_Password);

        sqLiteHelper = new SQLiteHelper(this);
        LogInButton.setOnClickListener(this);
        RegisterButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_Login:
                // Calling EditText is empty or no method.
                CheckEditTextStatus();

                // Calling login method.
                LoginFunction();
                break;

            case R.id.btn_SignUp:
                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

        }

    }

    // Login function starts from here.
    public void LoginFunction() {

        if (EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_4_Email + "=?", new String[]{MobileHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_5_Password));
                    UserName = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        } else {

            //If any of login EditText empty then this block will be executed.
            StaticMethod.showToastLong(this, getString(R.string.fill_username_password));

        }

    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus() {

        // Getting value from All EditText and storing into String Variables.
        MobileHolder = Mobile.getText().toString();
        PasswordHolder = Password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if (TextUtils.isEmpty(MobileHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult() {

        if (TempPassword.equalsIgnoreCase(PasswordHolder)) {

            StaticMethod.showToastLong(this, getString(R.string.login_successfully));

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(userName, UserName);

            startActivity(intent);


        } else {

            StaticMethod.showToastLong(this, getString(R.string.login_warning));

        }
        TempPassword = "NOT_FOUND";

    }
}