import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router'; // Import Router
import { JwtHelperService } from '@auth0/angular-jwt';
import { ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})


export class LoginComponent implements OnInit{

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  loginSuccess = false;
  sessionExpired: boolean = false;
  loginError: string | null = null; // Add this line


  constructor(private loginService: LoginService, private router: Router, private jwtHelper: JwtHelperService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sessionExpired = this.route.snapshot.queryParamMap.has('sessionExpired');
    if(this.loginService.isLoggedIn())
    {
      this.router.navigate(['/home']);
    }
  }

  onSubmit() {
    const { username, password } = this.loginForm.value;

    if (username && password) {
      this.loginService.login(username, password).subscribe(
        response => {
          console.log('Logged in!', response);
          this.loginSuccess = true;

          const token = response.jwt; // Get the JWT from the response
          const decodedToken = this.jwtHelper.decodeToken(token);
          const email = decodedToken.sub; // Extract the email from the decoded token


        localStorage.setItem('email', email);
        // Store the email in local storage


          const userType = decodedToken.authority[0].authority; // Get the user type from the decoded token // Get the user type from the decoded token
          localStorage.setItem('userType', userType);


          setTimeout(() => {
            // Add your navigation logic here
            switch (userType) {
              case 'Owner':
                this.router.navigate(['/owner-dashboard']);
                break;
              case 'Tenant':
                this.router.navigate(['/tenant-dashboard']);
                break;
              case 'Broker':
                this.router.navigate(['/broker-dashboard']);
                break;
              default:
                console.error('Invalid user type');
                break;
            }
          }, 2000);
        },
        error =>
        {
          console.error('Error occurred while logging in', error);
          this.loginError = error.error; // Set the error message
        }

      );
    } else {
      console.error('Username and password are required');
    }
  }

}

