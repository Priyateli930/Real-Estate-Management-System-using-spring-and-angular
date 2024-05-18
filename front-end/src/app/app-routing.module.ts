import { AuthGuardService } from './services/auth-guard.service';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UsersComponent } from './components/users/users.component';
import { LoginComponent } from './components/login/login.component';
import { ContactAssistanceComponent } from './components/contact-assistance/contact-assistance.component';
import { RatingsComponent } from './components/ratings/ratings.component';
import { OwnerDashboardComponent } from './components/owner-dashboard/owner-dashboard.component';
import { BrokerDashboardComponent } from './components/broker-dashboard/broker-dashboard.component';
import { TenantDashboardComponent } from './components/tenant-dashboard/tenant-dashboard.component';
import { ViewPropertyComponent } from './components/view-property/view-property.component';
import { AddPropertyComponent } from './components/add-property/add-property.component';
import { MydetailsComponent } from './components/mydetails/mydetails.component';
import { UploadedPropertyComponent } from './components/uploaded-property/uploaded-property.component';
import { PropertyTransactionsComponent } from './components/property-transactions/property-transactions.component';
import { TransactionHistoryComponent } from './components/transaction-history/transaction-history.component';





const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: "home", component: HomeComponent },
  {path: "users", component: UsersComponent },
  {path: "login", component: LoginComponent},
  {path: "contact", component: ContactAssistanceComponent, canActivate: [AuthGuardService]},
  {path: "ratings", component: RatingsComponent, canActivate: [AuthGuardService]},
  { path: "owner-dashboard", component: OwnerDashboardComponent, canActivate: [AuthGuardService]},
  { path: "tenant-dashboard", component: TenantDashboardComponent, canActivate: [AuthGuardService]},
  { path: "broker-dashboard", component: BrokerDashboardComponent, canActivate: [AuthGuardService]},
  { path: "view-property", component: ViewPropertyComponent, canActivate: [AuthGuardService]},
  { path: "add-new-property", component: AddPropertyComponent, canActivate: [AuthGuardService]},
  { path: "mydetails", component: MydetailsComponent, canActivate: [AuthGuardService]},
  { path: "uploaded-property", component: UploadedPropertyComponent, canActivate: [AuthGuardService]},
  { path: "property-transactions", component: PropertyTransactionsComponent, canActivate: [AuthGuardService]},
  { path: "transaction-history", component: TransactionHistoryComponent, canActivate: [AuthGuardService] }
  // { path: "protected", canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
