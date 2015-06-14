package edu.wayne.michmeditrans;

import edu.wayne.michmeditrans.R;
import edu.wayne.michmeditrans.R.id;
import edu.wayne.michmeditrans.R.layout;
import edu.wayne.michmeditrans.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

//package com.example.michmeditrans;


import android.database.sqlite.SQLiteDatabase;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class SignUpActivity extends Activity {

	
	Button etCreateAccount;
	String etFirstName, etLastName, etEmail,etUserName, etPassword, etPhone,etAccountNum;
	RadioButton Doctor,Patient, Driver;
	SQLiteDatabase database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		database=openOrCreateDatabase("MyDB1",MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Users(etFirstName VARCHAR,etLastName VARCHAR,etemail VARCHAR, etUserName VARCHAR,etPassword VARCHAR, );");
	
		Doctor = (RadioButton) findViewById(R.id.Doctor);
		Patient = (RadioButton) findViewById(R.id.Patient);
		Driver = (RadioButton) findViewById(R.id.Driver);
		etCreateAccount = (Button) findViewById(R.id.etCreateAccount);

		//etCreateAccount.setOnClickListener((OnClickListener) this);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void CreateAccountButton(View view)
	{
		  EditText edittext1=(EditText )findViewById(R.id.etFirstName);
		  EditText edittext2=(EditText )findViewById(R.id.etLastName);
		  EditText edittext3=(EditText )findViewById(R.id.etEmail);
		  EditText edittext7=(EditText )findViewById(R.id.etPhone);
		  EditText edittext4=(EditText )findViewById(R.id.etUserName);
		  EditText edittext5=(EditText )findViewById(R.id.etPassword);
		  EditText edittext8=(EditText )findViewById(R.id.etAccountNum);

		  etFirstName=edittext1.getText().toString();
		  etLastName=edittext2.getText().toString();
		  etEmail=edittext3.getText().toString();
		  etUserName=edittext4.getText().toString();
		  etPassword=edittext5.getText().toString();
		  etPhone=edittext7.getText().toString();
		  etAccountNum=edittext8.getText().toString();


		  
		  
	}
}
