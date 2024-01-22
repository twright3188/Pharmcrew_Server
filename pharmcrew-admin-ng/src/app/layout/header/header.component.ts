import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/session/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'layout-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private sessionService: SessionService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  name() {
    return localStorage.getItem('name');
  }

  onLogoutClick() {
    this.sessionService.logout().subscribe(response => {
      // this.sessionService.isLoggedIn = false;
      localStorage.removeItem('isLoggedIn');

      this.router.navigate(['login']);
    });
  }

}
