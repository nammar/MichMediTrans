//
//  DoctorTableViewCell.swift
//  MichMediTrans_iOS
//
//  Created by Nariman Ammar on 6/21/15.
//  Copyright (c) 2015 MichMediTrans. All rights reserved.
//

import UIKit

class DoctorTableViewCell: UITableViewCell {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var photoImageView: UIImageView!
    @IBOutlet weak var ratingControl: RatingControl!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
