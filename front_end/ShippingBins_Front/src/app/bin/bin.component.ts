import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";


export class Bin {
  constructor(
    public id: number,
    public name: string,
    public width: number,
    public length: number,
    public height: number,
    public price: number,
    public amount: number,
    public volume: number,
    public leasingPrice: number,
  ) {

  }
}

@Component({
  selector: 'app-bin',
  templateUrl: './bin.component.html',
  styleUrls: ['./bin.component.css']
})
export class BinComponent implements OnInit {

  bins: Bin[];
  closeResult: string;
  editForm: FormGroup;
  private deleteId: number;

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getBins();

    this.editForm = this.formBuilder.group( {
      id: [''],
      name: [''],
      width: [''],
      length: [''],
      height: [''],
      price: [''],
      amount: [''],
      volume: [''],
      leasingPrice: ['']
    });
  }
  getBins(){
    this.httpClient.get<any>('http://localhost:8080/bin/list').subscribe(
      response => {
        console.log(response);
        this.bins = response;
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
    const url = 'http://localhost:8080/bin/createBin';
    this.httpClient.post(url, f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }
  openDetails(targetModal, bin: Bin) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    document.getElementById('bName').setAttribute('value', bin.name);
    document.getElementById('bWidth').setAttribute('value', String(bin.width));
    document.getElementById('bLength').setAttribute('value', String(bin.length));
    document.getElementById('bHeight').setAttribute('value', String(bin.height));
    document.getElementById('bPrice').setAttribute('value', String(bin.price));
    document.getElementById('bAmount').setAttribute('value', String(bin.amount));
    document.getElementById('bVolume').setAttribute('value', String(bin.volume));
    document.getElementById('bLeasingPrice').setAttribute('value', String(bin.leasingPrice));
  }
  openEdit(targetModal, bin: Bin) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    this.editForm.patchValue( {
      id: bin.id,
      name: bin.name,
      width: bin.width,
      length: bin.length,
      height: bin.height,
      price: bin.price,
      amount: bin.amount,
      volume: bin.volume,
      leasingPrice: bin.leasingPrice,
    });
  }
  onSave() {
    const editURL = 'http://localhost:8080/bin/' + this.editForm.value.id + '/updateBin';
    this.httpClient.patch(editURL, this.editForm.value)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }

  openDelete(targetModal, bin: Bin) {
    this.deleteId = bin.id;
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
  }
  onDelete() {
    const deleteURL = 'http://localhost:8080/bin/' + this.deleteId + '/deleteBin';
    this.httpClient.delete(deleteURL)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }
}
