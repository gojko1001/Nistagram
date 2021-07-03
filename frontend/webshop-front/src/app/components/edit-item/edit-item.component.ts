import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemService } from 'src/app/service/item.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {

  id: any;
  item: any;
  message: any;

  constructor(private activatedRoute: ActivatedRoute, private itemService: ItemService, private router: Router,
    private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( params => {
      this.id = params.get('id');
      this.itemService.getItemById(this.id).subscribe( data => {
        this.item = data;
        if(this.item.username != this.getUsername()){
          this.router.navigate(['/discover']);
        }
        console.log(data);
      })
    })
  }

  getUsername(){
    return getUsernameFromToken();
  }

  updateItem(){
    this.itemService.updateItem(this.getUsername(), this.item).subscribe(data => {
      this.message = data;
      this.toastrService.success(this.message.error.text);
    }, error => {
      if(error.status == 200){
        this.toastrService.success(error.error.text);
        this.router.navigate(['/discover']);
      }else{
        this.toastrService.error(error.error.text);
      }
    });
  }

  back(){
    this.router.navigate(['/discover']);
  }

}
