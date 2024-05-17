import { Component, OnInit } from '@angular/core';
import { PropertiesService } from '../../services/properties.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { PropImagesService } from '../../services/prop-images.service';

@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrl: './add-property.component.css'
})
export class AddPropertyComponent implements OnInit{
  propertyForm!: FormGroup;
  propertyTypes = ['Flat', 'Villa', 'Land'];
  propertyStatus = ['Available', 'Rented', 'Sold'];
  propertyid:any;
  imageForm!: FormGroup;
  isSubmitted = false;
  imageCount = 0;

  constructor(private fb: FormBuilder, private propertyService: PropertiesService, private loginService: LoginService, private propImagesService: PropImagesService) { }

  ngOnInit() {
    this.propertyForm = this.fb.group({
      addressline1: ['', Validators.required],
      addressline2: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      pincode: ['', Validators.required],
      size: ['', Validators.required],
      price: ['', Validators.required],
      propertytype: [this.propertyTypes[0], Validators.required],
      propstatus: [this.propertyStatus[0], Validators.required]

    });

    this.imageForm = this.fb.group({
      propimages: ['', Validators.required]
    });

  }

  onSubmit() {
    console.log(this.propertyForm.value);
    if (this.propertyForm.valid) {
      const formData = this.propertyForm.value;
      const userId = localStorage.getItem('userId');
      const userType = localStorage.getItem('userType');

      if (userId && userType) {
        this.loginService.getUserById(userId).subscribe(user => {
          if (userType === 'Owner') {
            formData.owner = user;
            formData.agent = null;
          } else if (userType === 'Broker') {
            formData.agent = user;
            formData.owner = null;
          }
          console.log(formData);

          if (userType === 'Owner')
          {
            this.propertyService.addPropertyOwner(formData).subscribe(
              data => {
                console.log('Success!', data);
                // Store propertyId in local storage
                this.isSubmitted = true;
                localStorage.setItem('propertyId', data.propertyid);
              },
              error => console.error('Error!', error),
            );
          }


          if (userType === 'Broker')
          {
            this.propertyService.addPropertyBroker(formData).subscribe(
              data => {
                console.log('Success!', data);
                // Store propertyId in local storage
                this.isSubmitted = true;
                localStorage.setItem('propertyId', data.propertyid);
              },
              error => console.error('Error!', error),
            );
          }


        });
      } else {
        console.error('User ID or User Type is not available in local storage');
      }
    }
  }



selectedFile: File | null = null;
imageData: string | null = null;

onFileSelect(event: any) {
  if (event.target.files.length > 0) {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.imageData = e.target.result;
    };
    if (this.selectedFile) {
      reader.readAsDataURL(this.selectedFile);
    }
  }
}


onImageSubmit() {
  const propertyId = localStorage.getItem('propertyId');
  const userType = localStorage.getItem('userType');

  if (userType === 'Owner')
  {
    if (this.imageData && propertyId) {
      this.propImagesService.uploadImageOwner(propertyId, this.imageData).subscribe(
        (res) => console.log(res),
        (err) => console.log(err)
      );

      this.imageCount++;
    }
  }


  if (userType === 'Broker')
  {
    if (this.imageData && propertyId) {
      this.propImagesService.uploadImageBroker(propertyId, this.imageData).subscribe(
        (res) => console.log(res),
        (err) => console.log(err)
      );

      this.imageCount++;
    }
  }

  window.alert('Image Uploaded successfully');

  // Refresh the component


}



}
