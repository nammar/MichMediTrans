package edu.wayne.michmeditrans;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogIn extends Activity {

	
	Button signin,SignUp,ForgotPassword;
	EditText etUserName, etPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		
		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		signin = (Button) findViewById(R.id.signin);
		SignUp = (Button) findViewById(R.id.SignUp);
		ForgotPassword = (Button) findViewById(R.id.ForgotPassword);

		//signin.setOnClickListener((OnClickListener) this);
		//SignUp.setOnClickListener((OnClickListener) this);
		//ForgotPassword.setOnClickListener((OnClickListener) this);
		
	}
	public void buttonSignUpMethod(View view)
	{
		Intent intent = new Intent(LogIn.this,SignUpActivity.class);
		startActivity(intent);
		
	}

	public void SignInButton(View view)
	{
		//When the login button is clicked
		switch(view.getId()){
		case R.id.signin:
			
			
			break;
		}
			
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
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
}
