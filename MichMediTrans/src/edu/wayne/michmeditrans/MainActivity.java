package edu.wayne.michmeditrans;

import org.xmlpull.v1.sax2.Driver;

import edu.wayne.medical.PatientsActivity;
import edu.wayne.michmeditrans.R;
import edu.wayne.transportation.DriversActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This activity represents the Role selection page. It includes roles (doctor, patient, driver)
 * and takes the user to their corresponding login/sign in screen
 * @author narimanammar
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Button01 is the button that corresponds to the patient in activity_main.xml
		Button patient = (Button) findViewById(R.id.Button01);
		Button driver = (Button) findViewById(R.id.Button03);

		patient.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// switching screens is done in two steps
				//1. create intent 
				//Intent myIntent = new Intent(view.getContext(),PatientsActivity.class);
				Intent myIntent = new Intent(view.getContext(),
				LogIn.class);
				//2. launch activity
				startActivity(myIntent);
				
				
			}
		
			
			
		
		});
		
		driver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// switching screens is done in two steps
				//1. create intent 
				Intent myIntent = new Intent(view.getContext(),
						DriversActivity.class);
				//2. launch activity
				startActivity(myIntent);
				
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	public void buttonSignIn(View view)
	{
		Intent intent = new Intent(MainActivity.this,LogIn.class);
		startActivity(intent);
		
	}
	
	
}
