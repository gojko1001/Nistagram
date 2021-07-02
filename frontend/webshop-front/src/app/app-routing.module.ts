import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//components
import { NavbarComponent } from './components/navbar/navbar.component';
import { DiscoverComponent } from './components/discover/discover.component';

const routes: Routes = [
  { path:'', redirectTo:'discover', pathMatch: 'full' },
  { path:'discover', component: DiscoverComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
