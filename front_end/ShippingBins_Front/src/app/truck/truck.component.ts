import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";


export class Truck {
  constructor(
   public id: number,
   public trailerType: string,
   public regNumber: string,
   public trailerMaxVolume: number,
  ) {

  }
}

@Component({
  selector: 'app-truck',
  templateUrl: './truck.component.html',
  styleUrls: ['./truck.component.css']
})
export class TruckComponent implements OnInit {

  trucks: Truck[];
  closeResult: string;
  editForm: FormGroup;
  private deleteId: number;

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getTrucks();

    this.editForm = this.formBuilder.group( {
      id: [''],
      trailerType: [''],
      regNumber: [''],
      trailerMaxVolume: ['']
    });
  }
  getTrucks(){
    this.httpClient.get<any>('http://localhost:8080/truck/list').subscribe(
      response => {
        console.log(response);
        this.trucks = response;
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
    const url = 'http://localhost:8080/truck/createTruck';
    this.httpClient.post(url, f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }
  openDetails(targetModal, truck: Truck) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    document.getElementById('tType').setAttribute('value', truck.trailerType);
    document.getElementById('rNumber').setAttribute('value', truck.regNumber);
    document.getElementById('tMaxVolume').setAttribute('value', String(truck.trailerMaxVolume));
  }
  openEdit(targetModal, truck: Truck) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    this.editForm.patchValue( {
      id: truck.id,
      trailerType: truck.trailerType,
      regNumber: truck.regNumber,
      trailerMaxVolume: truck.trailerMaxVolume
    });
  }
  onSave() {
    const editURL = 'http://localhost:8080/truck/' + this.editForm.value.id + '/updateTruck';
    this.httpClient.patch(editURL, this.editForm.value)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }

  openDelete(targetModal, truck: Truck) {
    this.deleteId = truck.id;
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
  }
  onDelete() {
    const deleteURL = 'http://localhost:8080/truck/' + this.deleteId + '/deleteTruck';
    this.httpClient.delete(deleteURL)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }
}
