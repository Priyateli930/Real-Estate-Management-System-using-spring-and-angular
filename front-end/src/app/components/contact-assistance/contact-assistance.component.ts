import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ContactAssistanceService } from '../../services/contact-assistance.service';
import { Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';



@Component({
  selector: 'app-contact-assistance',
  templateUrl: './contact-assistance.component.html',
  styleUrl: './contact-assistance.component.css'
})



export class ContactAssistanceComponent implements OnInit {


    contactForm!: FormGroup;
    user: any;

    constructor(private contactAssistanceService: ContactAssistanceService, private loginService: LoginService) { }

    ngOnInit(): void {
      this.contactForm = new FormGroup({
        'name': new FormControl(null, Validators.required),
        'email': new FormControl(null, [Validators.required, Validators.email]),
        'contact': new FormControl(null, Validators.required),
        'subject': new FormControl(null, Validators.required),
        'message': new FormControl(null, Validators.required)
      });

      const email = localStorage.getItem('email'); // Get the email from local storage
      if (email) {
        this.loginService.getUserByEmail(email).subscribe(user => {
          this.user = user;
        });
      }
    }

    onSubmit() {
      if (this.contactForm.valid) {
        const contactAssistance = {
          user: this.user,
          cname: this.contactForm.value.name,
          cemail: this.contactForm.value.email,
          phone: this.contactForm.value.contact,
          subject: this.contactForm.value.subject,
          message: this.contactForm.value.message
        };
        this.contactAssistanceService.addContactAssistance(contactAssistance).subscribe(
          response => console.log('Contact Assistance added!', response),
          error => console.error('Error!', error)
        );
      }
    }
}
