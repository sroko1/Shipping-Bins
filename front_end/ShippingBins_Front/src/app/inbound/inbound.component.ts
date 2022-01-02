import { Component, OnInit } from '@angular/core';
import {DatePipe} from "@angular/common";
import {Bin} from "../bin/bin.component";
import {Truck} from "../truck/truck.component";
import {HttpClient} from "@angular/common/http";
import {Supplier} from "../supplier/supplier.component";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";

export class Inbound {
  constructor(
  public id: number,
  public incomingType: string,
  public quantity: number,
  public location: string,
  public arrived: Date,
  public bin: Bin,
  public truck: Truck,
  public supplier: Supplier
  ) {
  }
}

@Component({
  selector: 'app-inbound',
  templateUrl: './inbound.component.html',
  styleUrls: ['./inbound.component.css']
})
export class InboundComponent implements OnInit {

  inbounds: Inbound[];
  closeResult: string;
  editForm: FormGroup;
  private deleteId: number;

  constructor(
     private httpClient: HttpClient,
     private modalService: NgbModal,
     private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getInbounds();

    this.editForm = this.formBuilder.group( {
      id: [''],
      incomingType: [''],
      quantity: [''],
      location: [''],
      arrived: [''],
      bin: [''],
      truck: [''],
      supplier: ['']
    });
  }
  getInbounds(){
    this.httpClient.get<any>('http://localhost:8080/inbound/list').subscribe(
      response => {
        console.log(response);
        this.inbounds = response;
      }
    );
  }
  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  onSubmit(f: NgForm) {
    const url = 'http://localhost:8080/inbound/createInbound';
    this.httpClient.post(url, f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }
  openDetails(targetModal, inbound: Inbound) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    document.getElementById('iType').setAttribute('value', inbound.incomingType);
    document.getElementById('iQuantity').setAttribute('value', String(inbound.quantity));
    document.getElementById('iLocation').setAttribute('value', inbound.location);
    // @ts-ignore
    document.getElementById('iArrived').setAttribute('value', inbound.arrived);
    document.getElementById('iBinName').setAttribute('value', inbound.bin.name);
    document.getElementById('iTruckRegNumber').setAttribute('value', inbound.truck.regNumber);
    document.getElementById('iSupplierName').setAttribute('value', inbound.supplier.name);
  }
  openEdit(targetModal, inbound: Inbound) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    this.editForm.patchValue( {
      id: inbound.id,
      incomingType: inbound.incomingType,
      quantity: inbound.quantity,
      location: inbound.location,
      arrived: inbound.arrived,
      bin: inbound.bin.name,
      truck: inbound.truck.regNumber,
      supplier: inbound.supplier.name
    });
  }
  onSave() {
    const editURL = 'http://localhost:8080/inbound/' + this.editForm.value.id + '/updateInbound';
    this.httpClient.patch(editURL, this.editForm.value)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }
  openDelete(targetModal, inbound: Inbound) {
    this.deleteId = inbound.id;
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
  }
  onDelete() {
    const deleteURL = 'http://localhost:8080/inbound/' + this.deleteId + '/deleteInbound';
    this.httpClient.delete(deleteURL)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }
}
