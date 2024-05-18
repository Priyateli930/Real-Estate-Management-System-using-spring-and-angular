import { AuthGuardService } from './services/auth-guard.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsersComponent } from './components/users/users.component';
import { RatingsComponent } from './components/ratings/ratings.component';
import { PropertiesComponent } from './components/properties/properties.component';
import { PropertyTransactionsComponent } from './components/property-transactions/property-transactions.component';
import { PropImagesComponent } from './components/prop-images/prop-images.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContactAssistanceComponent } from './components/contact-assistance/contact-assistance.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { OwnerDashboardComponent } from './components/owner-dashboard/owner-dashboard.component';
import { ViewPropertyComponent } from './components/view-property/view-property.component';
import { AddPropertyComponent } from './components/add-property/add-property.component';
import { MydetailsComponent } from './components/mydetails/mydetails.component';
import { UploadedPropertyComponent } from './components/uploaded-property/uploaded-property.component';
import { BrokerDashboardComponent } from './components/broker-dashboard/broker-dashboard.component';
import { TenantDashboardComponent } from './components/tenant-dashboard/tenant-dashboard.component';
import { TransactionHistoryComponent } from './components/transaction-history/transaction-history.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptorService } from './services/auth-interceptor.service';



@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    RatingsComponent,
    PropertiesComponent,
    PropertyTransactionsComponent,
    PropImagesComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    ContactAssistanceComponent,
    OwnerDashboardComponent,
    ViewPropertyComponent,
    AddPropertyComponent,
    MydetailsComponent,
    UploadedPropertyComponent,
    BrokerDashboardComponent,
    TenantDashboardComponent,
    TransactionHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [ { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService, AuthGuardService,  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true },],
  bootstrap: [AppComponent]
})
export class AppModule { }
