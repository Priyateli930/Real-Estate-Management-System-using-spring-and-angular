import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Users } from '../../models/users';

@Component({
  selector: 'app-mydetails',
  templateUrl: './mydetails.component.html',
  styleUrl: './mydetails.component.css'
})
export class MydetailsComponent implements OnInit{
  user: Users | null = null;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
    const email = localStorage.getItem('email'); // Get the email from local storage

    if (email) {
      this.loginService.getUserByEmail(email).subscribe(user => {
        this.user = user;
      });
    }
  }
}
