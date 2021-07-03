import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//components
import { DiscoverComponent } from './components/discover/discover.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path:'', redirectTo:'/discover', pathMatch: 'full' },
  { path:'discover', component: DiscoverComponent },
  { path:'registration', component: RegistrationComponent },
  { path:'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
