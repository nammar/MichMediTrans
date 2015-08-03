package edu.wayne.transportation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
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
import edu.wayne.utils.Constants;
import edu.wayne.utils.FetchAddressIntentService;

public class PatientsViewTransActivity extends FragmentActivity implements
		GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback,
		ConnectionCallbacks, LocationListener {

	/**
	 * GoogleApiClient wraps our service connection to Google Play Services and
	 * provides access to the user's sign in state as well as the Google's APIs.
	 */
	protected GoogleApiClient mGoogleApiClient;

	// TODO: get from a json/csv files/ database etc. But for now hard coded
	// with 3 Driver objects
	private List<DriverData> drivers = null;

	MapView mapView;
	GoogleMap map;

	private Location mLastLocation;
	LocationRequest mLocationRequest;
	protected String mAddressOutput;
	protected TextView fromText;
	protected TextView toText;
	private AddressResultReceiver mResultReceiver;

	//private static final LatLng DETROIT = new LatLng(42.3314, -83.0458);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadSampleDrivers();
		setContentView(R.layout.activity_patients_view_trans);
		mResultReceiver = new AddressResultReceiver(new Handler());
		fromText = (TextView) findViewById(R.id.AutoCompleteTextView01);
		toText = (TextView) findViewById(R.id.autocomplete_places);
		buildGoogleApiClient();
		// mGoogleApiClient = new GoogleApiClient.Builder(this)
		// .enableAutoManage(this, 0, this).addApi(Places.GEO_DATA_API)
		// .build();

		MapFragment mapFragment = (MapFragment) getFragmentManager()
				.findFragmentById(R.id.mapview1);
		mapFragment.getMapAsync(this);
		map = mapFragment.getMap();
//		map.moveCamera(CameraUpdateFactory.newLatLngZoom(DETROIT, 15));
//		map.animateCamera(CameraUpdateFactory.zoomIn());
//		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//		CameraPosition cameraPosition = new CameraPosition.Builder()
//				.target(DETROIT) // Sets the center of the map to Mountain View
//				.zoom(10) // Sets the zoom
//				.bearing(90) // Sets the orientation of the camera to east
//				.tilt(30) // Sets the tilt of the camera to 30 degrees
//				.build(); // Creates a CameraPosition from the builder
//		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
	//	mLocationRequest = new LocationRequest();
		// startLocationUpdates();

		// Hardcoding drivers
		//DriverData driver1 = new DriverData();
		// TODO: JULIAN: set properties: name, picture, license plate, contact
		// info
		// TODO: ABDUL: look into how to retrieve driver location
		// drivers.add(driver1);

	}

	private void loadSampleDrivers() {
		DriverData driver1 = new DriverData();
		//driver1.setLocation();
		//driver2.setName();
		
		
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
	public void onConnected(Bundle connectionHint) {

		mLastLocation = LocationServices.FusedLocationApi
				.getLastLocation(mGoogleApiClient);

		if (mLastLocation != null) {
//			if (!Geocoder.isPresent()) {
//				Toast.makeText(this, "no geocoder", Toast.LENGTH_LONG).show();
//				return;
//			}
//
//				map.addMarker(new MarkerOptions().position(
//						new LatLng(65.9667, -18.5333
//						// mLastLocation.getLatitude(),mLastLocation.getLongitude()
//
//						))

//				.title("Your current location"
//
//				));
//			}

			// startIntentService();

			// this.fromText
			// .setText(String.valueOf(mLastLocation));

			// Log.i("main-activity, longitude",String.valueOf(mLastLocation));
			Log.i("main-activity, longitude",
					String.valueOf(mLastLocation.getLatitude()));
			Log.i("main-activity, latitude",
					String.valueOf(mLastLocation.getLongitude()));
			
			LatLng locc = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());

			//map.moveCamera(CameraUpdateFactory.newLatLngZoom(locc, 15));
			
			//map.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(locc).zoom(17).bearing(90).tilt(10).build(); 
			map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			

		}
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		Log.i("patients trans activity", "Connection suspended");
		mGoogleApiClient.connect();

	}

	protected void startLocationUpdates() {

		mLocationRequest.setInterval(10000);
		mLocationRequest.setFastestInterval(5000);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		if (mGoogleApiClient.isConnected()) {
			LocationServices.FusedLocationApi.requestLocationUpdates(
					mGoogleApiClient, mLocationRequest, this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		mLastLocation = location;
		if (mLastLocation != null) {
			Log.i("main-activity, longitude",
					String.valueOf(mLastLocation.getLatitude()));
			Log.i("main-activity, latitude",
					String.valueOf(mLastLocation.getLongitude()));
		}

	}

	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	@Override
	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

//	protected void startIntentService() {
//		Intent intent = new Intent(this, FetchAddressIntentService.class);
//		intent.putExtra(Constants.RECEIVER, mResultReceiver);
//		intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
//		startService(intent);
//	}

	class AddressResultReceiver extends ResultReceiver {
		public AddressResultReceiver(Handler handler) {
			super(handler);
		}

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {

			mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
			fromText.setText(mAddressOutput);

		}
	}
	
	@Override
	/**
	 * https://developers.google.com/maps/documentation/android/marker
	 */
	public void onMapReady(GoogleMap map) {
//		 map.addMarker(new MarkerOptions().position(
//		 new LatLng(
//		 42.3314, -83.0458
//		 )) // TODO: ABDUL: get this from
//		// // driver location
//		 .title("Nariman Ammar, Liscence plate 1234, cell 33388800, rating 5 stars"
//		// // +
//		// // driver1.getName()
//		 ));
		 map.setMyLocationEnabled(true);
		 Location loc = map.getMyLocation();
		 
//		 Geocoder geo = new Geocoder(this);
if(loc!=null){
		 Log.i("trans activity","location"+loc.toString());
	//	 try {
//			 
//			List<Address> locs =geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
//			fromText.setText(locs.get(0).toString());
//			 
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 }
		 

	}

}
