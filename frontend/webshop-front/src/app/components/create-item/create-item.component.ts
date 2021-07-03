import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemService } from 'src/app/service/item.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

  item: any = {username: this.getUsername()};
  message: any;

  constructor(private itemService: ItemService, private router: Router,
    private toastrService: ToastrService) { }

  ngOnInit(): void {
  }

  back(){
    this.router.navigate(['/discover']);
  }

  getUsername(){
    return getUsernameFromToken();
  }

  createItem(){
    this.itemService.createItem(this.item).subscribe(data => {
      this.toastrService.success("Item has been created.");
      this.router.navigate(['/discover']);
    }, error => {
        this.toastrService.error("Error occured");
    });
  }

}
