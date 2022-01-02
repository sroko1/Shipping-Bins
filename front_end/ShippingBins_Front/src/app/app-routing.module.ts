import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {TruckComponent} from "./truck/truck.component";
import {BinComponent} from "./bin/bin.component";
import {InboundComponent} from "./inbound/inbound.component";
import {SupplierComponent} from "./supplier/supplier.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'trucks', component: TruckComponent},
  {path: 'bins', component: BinComponent},
  {path: 'suppliers', component: SupplierComponent},
  {path: 'inbounds', component: InboundComponent},
  {path: '', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
