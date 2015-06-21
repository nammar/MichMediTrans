//
//  RatingControl.swift
//  MichMediTrans_iOS
//
//  Created by Nariman Ammar on 6/21/15.
//  Copyright (c) 2015 MichMediTrans. All rights reserved.
//

import UIKit

class RatingControl: UIView {
    // MARK: Properties
    
    var rating = 0 {
        didSet {
            setNeedsLayout()
        }
    }
    var ratingButtons = [UIButton]()
    var spacing: Int = 5
    var stars: Int = 5
    
    // MARK: Initialization
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        let filledStarImage = UIImage(named: "filledStar")
        let emptyStarImage = UIImage(named: "emptyStar")
        
        for _ in 0..<5 {
            let button = UIButton()
            
            button.setImage(emptyStarImage, forState: .Normal)
            button.setImage(filledStarImage, forState: .Selected)
//            button.setImage(filledStarImage, forState: [.Highlighted, .Selected])
            
            button.adjustsImageWhenHighlighted = false
            
            button.addTarget(self, action: "ratingButtonTapped:", forControlEvents: .TouchDown)
            ratingButtons += [button]
            addSubview(button)
        }
    }
    
    override func layoutSubviews() {
        // Set the button's width and height to a square the size of the frame's height.
        let buttonSize = Int(frame.size.height)
        var buttonFrame = CGRect(x: 0, y: 0, width: buttonSize, height: buttonSize)
        
        // Offset each button's origin by the length of the button plus spacing.
//        for (index, button) in ratingButtons.enumerate() {
//            buttonFrame.origin.x = CGFloat(index * (buttonSize + spacing))
//            button.frame = buttonFrame
//        }
        updateButtonSelectionStates()
    }
    
    // MARK: Button Action
    
    func ratingButtonTapped(button: UIButton) {
     //   rating = ratingButtons.indexOf(button)! + 1
        
        updateButtonSelectionStates()
    }
    
    func updateButtonSelectionStates() {
//        for (index, button) in ratingButtons.enumerate() {
//            // If the index of a button is less than the rating, that button should be selected.
//            button.selected = index < rating
//        }
    }
}
