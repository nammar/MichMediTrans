package edu.wayne.transportation;

import java.util.List;

import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.wayne.michmeditrans.R;
import edu.wayne.model.DriverData;

public class PatientsViewTransActivity extends FragmentActivity implements
		GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {

	/**
	 * GoogleApiClient wraps our service connection to Google Play Services and
	 * provides access to the user's sign in state as well as the Google's APIs.
	 */
	protected GoogleApiClient mGoogleApiClient;
	
	//TODO: get from a json/csv files/ database etc. But for now hard coded with 3 Driver objects
	private List<DriverData> drivers = null;
	
	
	
	MapView mapView;
	GoogleMap map;
	
	
	private static final LatLng DETROIT = new LatLng(42.3314, -83.0458);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patients_view_trans);

		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.enableAutoManage(this, 0, this).addApi(Places.GEO_DATA_API)
				.build();
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
			    .findFragmentById(R.id.mapview1);
			mapFragment.getMapAsync(this);
			
			map = mapFragment.getMap();

			// Move the camera instantly to Sydney with a zoom of 15.
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(DETROIT, 15));

			// Zoom in, animating the camera.
			map.animateCamera(CameraUpdateFactory.zoomIn());

			// Zoom out to zoom level 10, animating with a duration of 2 seconds.
			map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

			// Construct a CameraPosition focusing on Detroit and animate the camera to that position.
			CameraPosition cameraPosition = new CameraPosition.Builder()
			    .target(DETROIT)      // Sets the center of the map to Mountain View
			    .zoom(10)                   // Sets the zoom
			    .bearing(90)                // Sets the orientation of the camera to east
			    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
			    .build();                   // Creates a CameraPosition from the builder
			map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			
			//step 1 in location detection
			map.setMyLocationEnabled(true);

			//Hardcoding drivers
			DriverData driver1 = new DriverData();
			//TODO: JULIAN: set properties: name, picture, license plate, contact info
			//TODO: ABDUL: look into how to retrieve driver location
			//drivers.add(driver1);
			
			

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patients_view_trans, menu);
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

	/**
	 * Called when the Activity could not connect to Google Play services and
	 * the auto manager could resolve the error automatically. In this case the
	 * API is not available and notify the user.
	 * 
	 * @param connectionResult
	 *            can be inspected to determine the cause of the failure
	 */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {

		Log.e("patients trans activity",
				"onConnectionFailed: ConnectionResult.getErrorCode() = "
						+ connectionResult.getErrorCode());

		Toast.makeText(
				this,
				"Could not connect to Google API Client: Error "
						+ connectionResult.getErrorCode(), Toast.LENGTH_SHORT)
				.show();
	}

	@Override
/**
 * https://developers.google.com/maps/documentation/android/marker
 */
	public void onMapReady(GoogleMap map) {
		  map.addMarker(new MarkerOptions()
	        .position(new LatLng(42.3314, -83.0458)) //TODO: ABDUL: get this from driver location
	        .title("Driver info: " 
	        //+ 
	        //driver1.getName()
	        ));
		 
		
	}

}
