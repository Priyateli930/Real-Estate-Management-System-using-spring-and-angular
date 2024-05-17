import { Component, OnInit } from '@angular/core';
import { Properties } from '../../models/properties';
import { PropImages } from '../../models/prop-images';
import { UploadedPropertyService } from '../../services/uploaded-property.service';
import { NgForm } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RatingsService } from '../../services/ratings.service';
import { LoginService } from '../../services/login.service';
import { PropertyTransactionsService } from '../../services/property-transactions.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-uploaded-property',
  templateUrl: './uploaded-property.component.html',
  styleUrl: './uploaded-property.component.css'
})
export class UploadedPropertyComponent implements OnInit {

  uploadedProperties: Properties[] = [];
  propertyTypes = ['Flat', 'Villa', 'Land'];
  propertyStatus = ['Available', 'Rented', 'Sold'];
  transactionForm!: FormGroup; // Define transactionForm
  ratingForm!: FormGroup; // Define ratingForm
  userType!: string | null; // Define userType
  currentProperty!: Properties | null; // Define currentProperty to hold the property for which the transaction or rating is being added
  empty:boolean=false;


  pageNo: number = 0; // Declare pageNo here
  totalPages: number = 0; // Declare totalPages here

  constructor(private uploadedPropertiesService: UploadedPropertyService,private transactionService: PropertyTransactionsService, private ratingService: RatingsService, private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
    const userEmail = localStorage.getItem('email');

    this.uploadedPropertiesService.getAllProperties(this.pageNo, 40, 'price').subscribe((response: any) => {
      // console.log(response);
      const properties = response.content; // Extract the content array from the response
      console.log(properties);
      this.totalPages = response.totalPages;
      console.log(this.totalPages);
      if (userEmail) {
        this.uploadedProperties = properties.filter((property: { owner: { email: string; }; agent: { email: string; }; }) =>
          (property.owner && property.owner.email === userEmail) ||
          (property.agent && property.agent.email === userEmail)

        );

        if (this.uploadedProperties.length === 0) {
          this.empty = true;
        }

        this.uploadedProperties.forEach(property => {
          this.uploadedPropertiesService.getPropertyImages(property.propertyid).subscribe(
            (images: PropImages[]) => {
              property.images = images;
              console.log(images);
            },
            (error) => {
              console.error('Error fetching property images:', error);
            }
          );
        });

      }
      this.checkUserTransactions();
    });


    this.userType = localStorage.getItem('userType'); // Get the user type from local storage

    this.transactionForm = new FormGroup({
      'buyerEmail': new FormControl(null, [Validators.required, Validators.email]),
      'saleRentPrice': new FormControl(null, Validators.required),
      'transactionDate': new FormControl(null, Validators.required)
    });

    this.ratingForm = new FormGroup({
      'ratingValue': new FormControl(null, [Validators.required, Validators.min(1), Validators.max(5)]),
      'feedback': new FormControl(null, Validators.required),
      'dateOfFeedback': new FormControl(null, Validators.required)
    });


  }


  prevPage(): void {
    if (this.pageNo > 0) {
      this.pageNo--;
      this.ngOnInit();
    }
  }

  nextPage(): void {
    if (this.pageNo < this.totalPages - 1) {
      this.pageNo++;
      this.ngOnInit();
    }
  }

  goToPage(page: number): void {
    this.pageNo = page;
    this.ngOnInit();
  }



  prepareForTransaction(property: Properties) {
    this.currentProperty = property;
    this.transactionForm.reset();
  }

  prepareForRating(property: Properties) {
    this.currentProperty = property;
    this.ratingForm.reset();
  }

  propertyToEdit: Properties = {
    propertyid: undefined,
    owner: undefined,
    agent: undefined,
    addressline1: undefined,
    addressline2: undefined,
    state: undefined,
    city: undefined,
    pincode: undefined,
    size: undefined,
    price: undefined,
    propertytype: undefined,
    propstatus: undefined,
    images: undefined,
    ratings: [],
    transactions: [],
    userHasTransaction:undefined
  }; // Initialize as an empty object


editProperty(property: Properties): void {
  // Store the property to edit in a variable
  this.propertyToEdit = property;
}

onSubmit(form: NgForm): void {
  // Call the service method to update the property
  this.uploadedPropertiesService.updateProperty(this.propertyToEdit.propertyid, form.value).subscribe(updatedProperty => {
    // Replace the property in the uploadedProperties array with the updated property
    const index = this.uploadedProperties.findIndex(p => p.propertyid === updatedProperty.propertyid);
    this.uploadedProperties[index] = updatedProperty;

      // Close the modal
      window.alert('Property details updated successfully');

      // Refresh the component
      window.location.reload();
  });


}

  deleteProperty(propertyId: number): void {
    // Call the service method to delete the property
    this.uploadedPropertiesService.deleteProperty(propertyId).subscribe(() => {
      // Remove the property from the uploadedProperties array
      this.uploadedProperties = this.uploadedProperties.filter(property => property.propertyid !== propertyId);
    });
  }


  onSubmitRating() {
    if (this.ratingForm.valid && this.currentProperty) {
      const userEmail = localStorage.getItem('email');
      if (userEmail) {
        this.loginService.getUserByEmail(userEmail).subscribe(user => {
          const ratingData = {
            property: this.currentProperty,
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
  }

  onSubmitTransaction() {
    if (this.transactionForm.valid && this.currentProperty) {
      const buyerEmail = this.transactionForm.value.buyerEmail;
      const sellerEmail = localStorage.getItem('email');
      if (buyerEmail && sellerEmail) {
        this.loginService.getUserByEmail(buyerEmail).subscribe(buyer => {
          this.loginService.getUserByEmail(sellerEmail).subscribe(seller => {
            const transactionData = {
              property: this.currentProperty,
              buyertenant: buyer,
              salerentprice: this.transactionForm.value.saleRentPrice,
              transactiondate: this.transactionForm.value.transactionDate,
              sellerid: seller.userid
            };
            // Call the service method to add the transaction
            this.transactionService.addTransaction(transactionData).subscribe(
              response => console.log('Transaction added!', response),
              error => console.error('Error!', error)
            );
          });
        });
      }
    }

    window.alert('Property Transaction details Added Successfully');

    // Refresh the component
    // window.location.reload();

  }

  // userHasTransaction:boolean=false;

  checkUserTransactions() {
    this.uploadedProperties.forEach(property => {
      this.transactionService.getTransactionsByPropertyId(property.propertyid).subscribe(
        (transaction) => {
          // console.log(transaction);
          const userId = localStorage.getItem('userId');
          // console.log(userId);
          // console.log(transaction.sellerid);
          property.userHasTransaction = transaction.sellerid === Number(userId);
          // console.log(property.userHasTransaction);
        },
        (error) => {
          console.error('Error fetching transactions', error);
        }
      );
    });
  }



}
