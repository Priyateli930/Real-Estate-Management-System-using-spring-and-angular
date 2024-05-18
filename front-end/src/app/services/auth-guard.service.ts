import { Injectable } from '@angular/core';
import { CanActivate, Router} from '@angular/router';
import { LoginService } from './login.service';
import {ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) { }

  // canActivate(): boolean {
  //   if (!this.loginService.isLoggedIn()) {
  //     this.router.navigate(['/login']);
  //     return false;
  //   }

  //   return true;
  // }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.loginService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    }

    const userType = localStorage.getItem('userType');
    const url = state.url;

    if (userType === 'Owner' && (url === '/tenant-dashboard' || url === '/broker-dashboard')) {
      this.router.navigate(['/owner-dashboard']);
      return false;
    }

    if (userType === 'Broker' && (url === '/owner-dashboard' || url === '/tenant-dashboard')) {
      this.router.navigate(['/broker-dashboard']);
      return false;
    }

    if (userType === 'Tenant' && (url === '/owner-dashboard' || url === '/broker-dashboard')) {
      this.router.navigate(['/tenant-dashboard']);
      return false;
    }

    return true;
  }
}
