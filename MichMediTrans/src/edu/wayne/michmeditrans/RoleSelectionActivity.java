package edu.wayne.michmeditrans;

import edu.wayne.medical.PatientsActivity;
import edu.wayne.transportation.DriversActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RoleSelectionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Button01 is the button that corresponds to the patient in activity_main.xml
		Button patient = (Button) findViewById(R.id.Button01);
		Button driver = (Button) findViewById(R.id.Button03);
		Button doctor = (Button) findViewById(R.id.Button02);
		patient.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// switching screens is done in two steps
				//1. create intent 
				Intent myIntent = new Intent(view.getContext(),PatientsActivity.class);

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
	
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_role_selection);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.role_selection, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
