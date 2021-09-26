package com.example.relifems.login;

import android.content.Context;
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
import com.example.relifems.utils.StaticMethod;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //public static boolean NameHolder;
    public EditText Email, Password, Name, LastName, MobileNumber;
    public Button Register;
    public String NameHolder, LastNameHolder, MobileNumberHolder, EmailHolder, PasswordHolder;
    public Boolean EditTextEmptyHolder;
    public SQLiteDatabase sqLiteDatabaseObj;
    public String SQLiteDataBaseQueryHolder;
    public SQLiteHelper sqLiteHelper;
    public Cursor cursor;
    public String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindViews();

    }

    public void bindViews() {
        Register = findViewById(R.id.bt_Signup);
        Email = findViewById(R.id.et_Email);
        LastName = findViewById(R.id.et_LastName);
        MobileNumber = findViewById(R.id.et_Mobile);
        Password = findViewById(R.id.et_Password);
        Name = findViewById(R.id.et_Name);

        Register.setOnClickListener(this);
        sqLiteHelper = new SQLiteHelper(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_Signup:

                // Creating SQLite database if doesn't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if doesn't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Email is already exists or not.
                CheckingMobileAlreadyExistsOrNot();

                //Sending confirmation email.
                Confirm();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();

                break;
        }

    }

    public void Confirm() {

    }

    // SQLite database build method.
    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_LastName + " VARCHAR, " + SQLiteHelper.Table_Column_3_Mobile + " VARCHAR, " + SQLiteHelper.Table_Column_4_Email + " VARCHAR, " + SQLiteHelper.Table_Column_5_Password + " VARCHAR);");

    }

    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase() {

        // If editText is not empty then this block will executed.
        if (EditTextEmptyHolder == true) {

            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (name,lastname,mobile,email,password) VALUES('" + NameHolder + "', '" + LastNameHolder + "','" + MobileNumberHolder + "','" + EmailHolder + "', '" + PasswordHolder + "');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.
            StaticMethod.showToastLong(this, getString(R.string.registration_toast));
            finish();

        }
        // This block will execute if any of the registration EditText is empty.
        else {
            // Printing toast message if any of EditText is empty.
            StaticMethod.showToastLong(this, getString(R.string.required_text));

        }

    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert() {

        Name.getText().clear();

        LastName.getText().clear();

        MobileNumber.getText().clear();

        Email.getText().clear();

        Password.getText().clear();

    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus() {

        // Getting value from All EditText and storing into String Variables.
        NameHolder = Name.getText().toString();
        LastNameHolder = Name.getText().toString();
        MobileNumberHolder = Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(LastNameHolder) || TextUtils.isEmpty(MobileNumberHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;
        }
    }

    // Checking Email is already exists or not.
    public void CheckingMobileAlreadyExistsOrNot() {

        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_3_Mobile + "=?", new String[]{MobileNumberHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Mobile Number Found";

                // Closing cursor.
                cursor.close();
            }
        }

        // Calling method to check final result and insert data into SQLite database.into SQLite da
        CheckFinalResult();

    }


    // Checking result
    public void CheckFinalResult() {

        // Checking whether email is already exists or not.
        if (F_Result.equalsIgnoreCase("Mobile Number Found")) {

            // If email is exists then toast msg will display.
            StaticMethod.showToastLong(this, getString(R.string.already_exits));

        } else {

            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found";

    }

}