import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//components
import { DiscoverComponent } from './components/discover/discover.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { EditItemComponent } from './components/edit-item/edit-item.component';
import { CreateItemComponent } from './components/create-item/create-item.component';

const routes: Routes = [
  { path:'', redirectTo:'/discover', pathMatch: 'full' },
  { path:'discover', component: DiscoverComponent },
  { path:'registration', component: RegistrationComponent },
  { path:'login', component: LoginComponent},
  { path:'edit-item/:id', component: EditItemComponent},
  { path:'create-item', component:CreateItemComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
