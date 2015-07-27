//
//  DriverLookupViewController.swift
//  MichMediTrans_iOS
//
//  Created by Nariman Ammar on 7/19/15.
//  Copyright (c) 2015 MichMediTrans. All rights reserved.
//

import UIKit
import MapKit

class DriverLookupViewController: UIViewController,MKMapViewDelegate {

    @IBOutlet weak var mapView: MKMapView!
    
    @IBOutlet weak var toAddressField: UITextField!
    
    let initialLocation = CLLocation(latitude: 52.3740300, longitude: 4.8896900)
   //TODO: make it 7 miles
    let searchRadius: CLLocationDistance = 2000
    
    override func viewDidLoad() {
        super.viewDidLoad()
         self.mapView.delegate = self
        let coordinateRegion = MKCoordinateRegionMakeWithDistance(initialLocation.coordinate, searchRadius * 2.0, searchRadius * 2.0)
        mapView.setRegion(coordinateRegion, animated: true)

        // Do any additional setup after loading the view.
       // searchInMap()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func searchInMap() {
        // 1
        let request = MKLocalSearchRequest()
        request.naturalLanguageQuery = "Detroit"
        //toAddressField.text
            //segmentedControl.titleForSegmentAtIndex(segmentedControl.selectedSegmentIndex)
        // 2
        let span = MKCoordinateSpan(latitudeDelta: 0.1, longitudeDelta: 0.1)
        request.region = MKCoordinateRegion(center: initialLocation.coordinate, span: span)
        // 3
        let search = MKLocalSearch(request: request)
        search.startWithCompletionHandler {
            (response: MKLocalSearchResponse!, error: NSError!) in
            
            for item in response.mapItems as! [MKMapItem] {
                println("Item name = \(item.name)")
                println("Latitude = \(item.placemark.location.coordinate.latitude)")
                println("Longitude = \(item.placemark.location.coordinate.longitude)")
            }
        }     
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
