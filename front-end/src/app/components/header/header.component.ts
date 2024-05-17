import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})

export class HeaderComponent {

  constructor(private router: Router, public loginService: LoginService) { }

  navigateToDashboard() {
    const userType = localStorage.getItem('userType');
    console.log(userType);
    if (userType === 'Owner') {
      this.router.navigate(['/owner-dashboard']);
    } else if (userType === 'Broker') {
      this.router.navigate(['/broker-dashboard']);
    } else if (userType === 'Tenant') {
      this.router.navigate(['/tenant-dashboard']);
    }
  }



  logout(): void {
    localStorage.removeItem('email'); // Remove the user's email from local storage
    localStorage.clear();
    location.reload();
  }
}
