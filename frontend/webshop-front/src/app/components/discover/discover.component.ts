import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemService } from 'src/app/service/item.service';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.component.html',
  styleUrls: ['./discover.component.css']
})
export class DiscoverComponent implements OnInit {

  items: any[]=[];

  constructor(private itemService: ItemService,
    private toastrService: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
    this.itemService.getItems().subscribe((data: any) => {
      this.items = data;
      console.log(this.items);
    }, error => {
      this.toastrService.error("Something went wrong. Please check your input!");
    });
  }

  addNewItem(){
    //todo
  }

}
