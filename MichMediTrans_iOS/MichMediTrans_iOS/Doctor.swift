//
//  Doctor.swift
//  MichMediTrans_iOS
//
//  Created by Nariman Ammar on 6/21/15.
//  Copyright (c) 2015 MichMediTrans. All rights reserved.
//


import UIKit

class Doctor {
    // MARK: Properties
    
    var name: String
    var photo: UIImage?
    var rating: Int
    
    // MARK: Initialization
    
    init?(name: String, photo: UIImage?, rating: Int) {
        // Initialize stored properties.
        self.name = name
        self.photo = photo
        self.rating = rating
        
        // Initialization should fail if there is no name or if the rating is negative.
        if name.isEmpty || rating < 0 {
            return nil
        }
    }
    
}