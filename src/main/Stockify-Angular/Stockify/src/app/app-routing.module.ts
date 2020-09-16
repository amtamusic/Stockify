import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PortfoliodashboardComponent } from './components/portfoliodashboard/portfoliodashboard.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'portfolio/dashboard', component: PortfoliodashboardComponent},
  { path: '', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
