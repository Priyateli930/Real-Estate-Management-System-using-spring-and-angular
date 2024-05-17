import { Component, NgModule, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})



export class UsersComponent implements OnInit  {

  profileForm = new FormGroup({
    username: new FormControl('', Validators.required),
    usertype: new FormControl('', Validators.required),
    contactno: new FormControl('', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]), // Indian mobile number validation
    email: new FormControl('', [Validators.required, Validators.email]),
    addressline1: new FormControl('', Validators.required),
    addressline2: new FormControl(''),
    city: new FormControl('', Validators.required),
    state: new FormControl('', Validators.required),
    pincode: new FormControl('', [Validators.required, Validators.pattern("^[1-9][0-9]{5}$")]), // Indian pincode validation
    dateofbirth: new FormControl('', Validators.required),
    verified: new FormControl('Verified'),
    passwordhash: new FormControl('', [Validators.required, Validators.minLength(8)]), // Minimum length of 8 characters
    ownedProperties: new FormControl([]),
    managedProperties: new FormControl([]),
    ratings: new FormControl([]),
    transactions: new FormControl([]),
    contactAssistances: new FormControl([]),
  });
  constructor(private usersService: UsersService, private router: Router) { }

  ngOnInit() {
  }

  registrationSuccess = false;

  onSubmit() {
    this.usersService.registerUser(this.profileForm.value)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.registrationSuccess = true;

          setTimeout(() => {
            if (this.registrationSuccess) {
              this.router.navigate(['/home']);
            }
          }, 2000);
        },
        error: (error) => {
          console.error(error);
          this.registrationSuccess = false;
        }
      });
  }


  // onSubmit() {
    // TODO: Use EventEmitter with form value
    // console.warn(this.profileForm.value);
    // return this.http.post('http://localhost:8080/api/register', this.profileForm);

  // }

    // Add your navigation logic here





}
