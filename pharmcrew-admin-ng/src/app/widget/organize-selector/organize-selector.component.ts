import { Component, OnInit, Input } from '@angular/core';
import { CommonService } from 'src/app/domain/common/common.service';
import { OrganizeListReq } from 'src/app/domain/common/organize-list-req';
import { Organize } from 'src/app/domain/common/organize';
import { v4 as uuid } from 'uuid';
import { Organize2 } from 'src/app/domain/common/organize2';

@Component({
  selector: 'widget-organize-selector',
  templateUrl: './organize-selector.component.html',
  styleUrls: ['./organize-selector.component.css']
})
export class OrganizeSelectorComponent implements OnInit {
  @Input() disabled: boolean = false;

  req: OrganizeListReq = new OrganizeListReq();
  organizess: Organize[][] = [];

  organize1Id: number;
  organize2Id: number;
  organize3Id: number;

  organize1Enable = true;
  organize2Enable = true;
  organize3Enable = true;

  constructor(
    private commonService: CommonService,
  ) { }

  ngOnInit(): void {
    this.organizeList();

    const organize_ = localStorage.getItem('organize');
    console.log(`organize_: ${organize_}`);
    if (organize_ !== null) {
      const organize: Organize2 = JSON.parse(organize_);
      this.setOrganizeIds(organize.ids, true);
    }
  }

  onOrganizeChange(depth: number) {
    console.log(`depth: ${depth}`);
    var value: any;
    switch (depth) {
      case 1:
        value = this.organize1Id;
        if (value === 'undefined') {
          this.organize1Id = undefined;
          this.organize2Id = undefined;
          this.organize3Id = undefined;

          this.organizess[1] = undefined;
          this.organizess[2] = undefined;
        } else {
          this.req.organizeId = this.organize1Id;
          this.req.depth = depth;
          
          this.organizeList();
        }
        break;
      case 2:
        value = this.organize2Id;
        if (value === 'undefined') {
          this.organize2Id = undefined;
          this.organize3Id = undefined;

          this.organizess[2] = undefined;
        } else {
          this.req.organizeId = this.organize2Id;
          this.req.depth = depth;

          this.organizeList();
        }
        break;
      case 3:
        value = this.organize3Id;
        if (value === 'undefined') {
          this.organize3Id = undefined;
        }
        break;
    }
    console.log(`organize1Id: ${this.organize1Id}, organize2Id: ${this.organize2Id}, organize3Id: ${this.organize3Id}`);
  }

  organizeId(): number {
    if (this.organize3Id !== undefined) {
      return this.organize3Id;
    } else if (this.organize2Id !== undefined) {
      return this.organize2Id;
    }
    return this.organize1Id;
  }

  organizeIdByDepth(depth: number): number {
    switch (depth) {
      case 1:
        return this.organize1Id;
      case 2:
        return this.organize2Id;
      case 3:
        return this.organize3Id;
    }
  }

  setOrganizeIds(ids: number[], withDisable?: boolean) {
    if (ids === undefined) {
      this.organize1Id = undefined;
      this.organize2Id = undefined;
      this.organize3Id = undefined;
      this.organizeList();
      return;
    }
    switch (ids.length) {
      case 1:
        this.organize1Id = ids[0];
        this.organize2Id = undefined;
        this.organize3Id = undefined;
        this.onOrganizeChange(1);
        if (withDisable)  this.organize1Enable = false;
        break;
      case 2:
        this.organize1Id = ids[0];
        this.organize2Id = ids[1];
        this.organize3Id = undefined;
        this.onOrganizeChange(1);
        if (withDisable)  this.organize1Enable = false;
        if (withDisable)  this.organize2Enable = false;
        break;
      case 3:
        this.organize1Id = ids[0];
        this.organize2Id = ids[1];
        this.organize3Id = ids[2];
        this.onOrganizeChange(1);
        if (withDisable)  this.organize1Enable = false;
        if (withDisable)  this.organize2Enable = false;
        if (withDisable)  this.organize3Enable = false;
        break;
    }
  }

  setOrganizeId(depth: number, id: number) {
    switch (depth) {
      case 1:
        this.organize1Id = id;
        break;
      case 2:
        this.organize2Id = id;
        break;
      case 3:
        this.organize3Id = id;
        break;
    }
  }

  isOrganizeEnable(depth: number): boolean {
    switch (depth) {
      case 1:
        return this.organize1Enable;
      case 2:
        return this.organize2Enable;
      case 3:
        return this.organize3Enable;
    }
  }

  setOrganizeEnable(depth: number, enable: boolean) {
    switch (depth) {
      case 1:
        this.organize1Enable = enable;
        break;
      case 2:
        this.organize2Enable = enable;
        break;
      case 3:
        this.organize3Enable = enable;
        break;
    }
  }

  backupCondition() {
    let ids: number[] = [];
    if (this.organize1Id !== undefined) {
      ids.push(this.organize1Id);
      if (this.organize2Id !== undefined) {
        ids.push(this.organize2Id);
        if (this.organize3Id !== undefined) {
          ids.push(this.organize3Id);
        }
      }
    }
    if (ids.length > 0) localStorage.setItem('organize-ids', JSON.stringify(ids));

    localStorage.setItem('organize-1enable', this.organize1Enable ? 'Y' : 'N');
    localStorage.setItem('organize-2enable', this.organize2Enable ? 'Y' : 'N');
    localStorage.setItem('organize-3enable', this.organize3Enable ? 'Y' : 'N');
  }
  
  restoreCondition() {
    setTimeout(() => {
      if (localStorage.getItem('organize-ids') !== undefined) {
        const ids = JSON.parse(localStorage.getItem('organize-ids'));
        this.setOrganizeIds(ids);
      }
      this.organize1Enable = localStorage.getItem('organize-1enable') === 'Y';
      this.organize2Enable = localStorage.getItem('organize-2enable') === 'Y';
      this.organize3Enable = localStorage.getItem('organize-3enable') === 'Y';
  
      localStorage.removeItem('organize-ids');
      localStorage.removeItem('organize-1enable');
      localStorage.removeItem('organize-2enable');
      localStorage.removeItem('organize-3enable');
    }, 100);
  }

  private organizeList() {
    this.commonService.organizeList(this.req).subscribe(response => {
      let resp = response.body;
      if (resp.code === 200) {
        if (resp.depth === undefined) {
          this.organizess[0] = resp.organizes;
        } else {
          this.organizess[this.req.depth] = resp.organizes;
        }

        switch (resp.depth) {
          case 1:
            if (this.organize2Id !== undefined) this.onOrganizeChange(2);
            break;
          case 2:
            if (this.organize3Id !== undefined) this.onOrganizeChange(3);
            break;
        }
      }
    })
  }

}
