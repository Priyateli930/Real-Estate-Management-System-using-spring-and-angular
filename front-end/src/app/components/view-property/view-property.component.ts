import { Component, OnInit } from '@angular/core';
import { ViewPropertyService } from '../../services/view-property.service';
import { Properties } from '../../models/properties';
import { PropImages } from '../../models/prop-images';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RatingsService } from '../../services/ratings.service';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';




@Component({
  selector: 'app-view-property',
  templateUrl: './view-property.component.html',
  styleUrl: './view-property.component.css'
})
export class ViewPropertyComponent implements OnInit{
  properties: Properties[] = [];
  selectedProperty!: Properties;
  ratingForm!: FormGroup; // Define ratingForm
  userType!: string | null; // Define userType

  // Add this method to handle the click event of the "See all images" button
  selectProperty(property: Properties): void {
    this.selectedProperty = property;
  }
  city: string='';
  minPrice: number=0;
  maxPrice: number=0;
  propertyType: string='';
  propertyStatus: string='';

  pageNo: number = 0; // Declare pageNo here
  totalPages: number = 0; // Declare totalPages here
  noPropertiesFound: boolean = false;




  constructor(private viewPropertyService: ViewPropertyService, private ratingService: RatingsService, private loginService: LoginService, private location: Location) { }

  ngOnInit(): void {
    // Remove the getProperties() call from here
    this.userType = localStorage.getItem('userType'); // Get the user type from local storage

    this.ratingForm = new FormGroup({
      'ratingValue': new FormControl(null, [Validators.required, Validators.min(1), Validators.max(5)]),
      'feedback': new FormControl(null, Validators.required),
      'dateOfFeedback': new FormControl(null, Validators.required)
    });
  }

  // Add this method to handle the form submission
  onSubmit(): void {
    const usertype = localStorage.getItem('userType');

    if(usertype==='Owner'){
      this.viewPropertyService.getPropertiesOwner(this.city, this.minPrice, this.maxPrice, this.propertyStatus, this.propertyType, this.pageNo, 6, 'price').subscribe((response: any) => {
        this.properties = response.content || [];
        this.totalPages = response.totalPages; // Set totalPages here
        this.noPropertiesFound = this.properties.length === 0;
        this.properties.forEach(property => {
          this.viewPropertyService.getPropertyImages(property.propertyid).subscribe((images: PropImages[]) => {
            property.images = images;
          });
        });
      });
    }


    if(usertype==='Broker'){
      this.viewPropertyService.getPropertiesBroker(this.city, this.minPrice, this.maxPrice, this.propertyStatus, this.propertyType, this.pageNo, 6, 'price').subscribe((response: any) => {
        this.properties = response.content || [];
        this.totalPages = response.totalPages; // Set totalPages here
        this.noPropertiesFound = this.properties.length === 0;
        console.log(this.noPropertiesFound);
        this.properties.forEach(property => {
          this.viewPropertyService.getPropertyImages(property.propertyid).subscribe((images: PropImages[]) => {
            property.images = images;
          });
        });
      });
    }



    if(usertype==='Tenant'){
      this.viewPropertyService.getPropertiesTenant(this.city, this.minPrice, this.maxPrice, this.propertyStatus, this.propertyType, this.pageNo, 6, 'price').subscribe((response: any) => {
        this.properties = response.content || [];
        this.totalPages = response.totalPages; // Set totalPages here
        this.noPropertiesFound = this.properties.length === 0;
        this.properties.forEach(property => {
          this.viewPropertyService.getPropertyImages(property.propertyid).subscribe((images: PropImages[]) => {
            property.images = images;
          });
        });
      });
    }




  }


  prevPage(): void {
    if (this.pageNo > 0) {
      this.pageNo--;
      this.onSubmit();
    }
  }

  nextPage(): void {
    if (this.pageNo < this.totalPages - 1) {
      this.pageNo++;
      this.onSubmit();
    }
  }

  goToPage(page: number): void {
    this.pageNo = page;
    this.onSubmit();
  }



  prepareForRating(property: Properties) {
    this.selectedProperty = property;
    this.ratingForm.reset();
  }

  onSubmitRating() {
    if (this.ratingForm.valid && this.selectedProperty) {
      const userEmail = localStorage.getItem('email');
      if (userEmail) {
        this.loginService.getUserByEmail(userEmail).subscribe(user => {
          const ratingData = {
            property: this.selectedProperty,
            user: user,
            ratingvalue: this.ratingForm.value.ratingValue,
            feedback: this.ratingForm.value.feedback,
            dateoffeedback: this.ratingForm.value.dateOfFeedback
          };
          // Call the service method to add the rating
          this.ratingService.addRating(ratingData).subscribe(
            response => console.log('Rating added!', response),
            error => console.error('Error!', error)
          );
        });
      }
    }

    window.alert('Rating details added successfully');

    // Refresh the component
   // Refresh the page
  //  window.location.reload();

  }

}
