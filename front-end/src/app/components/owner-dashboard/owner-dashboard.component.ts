import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Users } from '../../models/users';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-owner-dashboard',
  templateUrl: './owner-dashboard.component.html',
  styleUrl: './owner-dashboard.component.css'
})
export class OwnerDashboardComponent implements OnInit{

  userName: string='';
  constructor(private router: Router, private http: HttpClient, private loginService: LoginService) { }

  ngOnInit() {
    const email = localStorage.getItem('email'); // Get the email from local storage

    if (email) {
      this.loginService.getUserByEmail(email).subscribe(user => {
      this.userName = user.username;
      localStorage.setItem('userId', user.userid);
      localStorage.setItem('userName', user.username);
      localStorage.setItem('userType', user.usertype);
      });
    }
  }

  currentView: string = '';

  openNav() {
    document.getElementById("mySidenav")!.style.width = "300px";
    (document.querySelector(".main-content") as HTMLElement)!.style.marginLeft = "300px";
  }

  closeNav() {
    document.getElementById("mySidenav")!.style.width = "0";
    (document.querySelector(".main-content") as HTMLElement)!.style.marginLeft = "0";
  }


  // logout() {
    // your logout logic here

    // then clear localStorage
  //   localStorage.clear();
  // }
}


