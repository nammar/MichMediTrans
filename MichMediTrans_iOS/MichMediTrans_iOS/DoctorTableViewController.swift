//
//  DoctorTableViewController.swift
//  MichMediTrans_iOS
//
//  Created by Nariman Ammar on 6/21/15.
//  Copyright (c) 2015 MichMediTrans. All rights reserved.
//

import UIKit

class DoctorTableViewController: UITableViewController,UITableViewDelegate {
    var doctors = [Doctor]();

    
    @IBAction func addDoctor(sender: UIBarButtonItem) {
        
    }
  
    
    override func viewDidLoad() {
        super.viewDidLoad()

        loadSampleDoctors()
       
    }
    
    
    func loadSampleDoctors(){
        let photo1 = UIImage(named: "female.jpg")!
        let doctor1 = Doctor(name: "Nariman Ammar", photo: photo1, rating: 4)!
        
        let photo2 = UIImage(named: "male.jpg")!
        let doctor2 = Doctor(name: "Anthony Blackmon", photo: photo2, rating: 5)!
        
        let photo3 = UIImage(named: "female.jpg")!
        let doctor3 = Doctor(name: "Amney Iskandar", photo: photo3, rating: 3)!
        
        doctors+=[doctor1,doctor2,doctor3]
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return doctors.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("DoctorTableViewCell", forIndexPath: indexPath) as! DoctorTableViewCell
        
            // Fetches the appropriate doctor for the data source layout.
        let doctor = doctors[indexPath.row]

        // Configure the cell...
        cell.nameLabel.text = doctor.name
        cell.photoImageView.image = doctor.photo
        //cell.ratingControl.rating = doctor.rating
        
        return cell
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the item to be re-orderable.
        return true
    }
    */

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using [segue destinationViewController].
        if segue.identifier == "DoctorTableViewCell" {
            let detailVC: DoctorViewController = segue.destinationViewController as! DoctorViewController
            let indexPath = self.tableView.indexPathForSelectedRow()
//            let thisTask = self.objectAtIndexPath(indexPath!) as Doctor
//            detailVC.Doctor = thisTask
//            detailVC.delegate = self
            

    }
        
    }
}
