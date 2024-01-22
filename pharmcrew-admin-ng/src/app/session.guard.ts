import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionService } from './session/session.service';

@Injectable({
  providedIn: 'root'
})
export class PermissionGuard implements CanActivate {
  constructor(
    private sessionService: SessionService,
    private router: Router,
  ) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let url = state.url;

    return this.checkLogin(url) && this.checkAuthority(url);
  }

  private checkLogin(url: string): boolean {
    // if (this.sessionService.isLoggedIn) {
    if (localStorage.getItem('isLoggedIn') === 'Y') {
      return true;
    }

    this.sessionService.redirectUrl = url;

    this.router.navigate(['login']);
  }

  private checkAuthority(url: string): boolean {
    const authority = localStorage.getItem('authority');
    console.log(`url: ${url}, authority: ${authority}`);
    if ((
      url.startsWith('/partners') ||
      url.startsWith('/popups') ||
      url.startsWith('/banners') ||
      url.startsWith('/admsgs')
      ) && authority !== 'S') {
      alert('접근 권한이 없습니다.');

      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
  
}
