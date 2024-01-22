import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  // baseUrl = 'http://localhost:8080/pharmcrew-admin';
  baseUrl = environment.apiBaseUrl;

  redirectUrl: string;

  constructor(
    private http: HttpClient,
    private router: Router,
  ) { }

  downloadFile(fileId: number) {
    window.open(this.baseUrl + '/files/' + fileId);
  }

  get<Resp>(path: string, req?: any): Observable<HttpResponse<Resp>> {
    return this.http.get<Resp>(
      this.baseUrl + path,
      {
        withCredentials: true,
        params: this.undefined2Null(req),
        observe: 'response'
      }
    ).pipe(
      tap(
        data => this.handleData(data),
        error => this.handleError(error),
      )
    );
  }

  post<Resp>(path: string, req: any): Observable<HttpResponse<Resp>> {
    return this.http.post<Resp>(
      this.baseUrl + path,
      this.makeParam(req),
      {
        withCredentials: true,
        // headers: headers,
        observe: 'response',
      }
    ).pipe(
      tap(
        data => this.handleData(data),
        error => this.handleError(error),
      )
    );
  }

  postMultipart<Resp>(path: string, req: any): Observable<HttpResponse<Resp>> {
    return this.http.post<Resp>(
      this.baseUrl + path,
      this.makeData(req),
      {
        withCredentials: true,
        observe: 'response',
      }
    ).pipe(
      tap(
        data => this.handleData(data),
        error => this.handleError(error),
      )
    );
  }

  put<Resp>(path: string, req: any): Observable<HttpResponse<Resp>> {
    return this.http.put<Resp>(
      this.baseUrl + path,
      // this.makeParam(req),
      undefined,
      {
        withCredentials: true,
        params: this.undefined2Null(req),
        observe: 'response',
      }
    ).pipe(
      tap(
        data => this.handleData(data),
        error => this.handleError(error),
      )
    );
  }

  delete<Resp>(path: string, req?: any): Observable<HttpResponse<Resp>> {
    // const headers = new HttpHeaders({
    //   'Content-Type': 'application/x-www-form-urlencoded',
    // });
    // if (!Memory.Inst.getCookie()) {
    //   headers.append('Cookie', Memory.Inst.getCookie());
    // }
    return this.http.delete<Resp>(
      this.baseUrl + path,
      {
        withCredentials: true,
        // headers: headers,
        params: this.makeParam(req),
        observe: 'response',
      }
    ).pipe(
      tap(
        data => this.handleData(data),
        error => this.handleError(error),
      )
    );
  }

  private handleData<Resp>(data: HttpResponse<Resp>) {
    let resp = data.body;
    // console.log(`resp: ${JSON.stringify(resp)}`);
    if (resp['code'] !== 200)  alert(resp['message']);
  }

  private handleError(error: HttpErrorResponse) {
    console.log(`error: ${error}`);
    if (error.error instanceof ErrorEvent) {  // 클라이언트나 네트워크 문제
      alert('서버와의 통신이 원할하지 않습니다.\n잠시 후 다시 시도하시기 바랍니다.');
    } else {  // 백엔드에서 실패 처리
      if (error.status === 401) {
        localStorage.removeItem('isLoggedIn');

        this.redirectUrl = this.router.url;

        this.router.navigate(['/login']);

        // return Observable.throw('Unauthorized');
      }
    }
    return throwError(error);
  }

  private makeParam(req?: any) {
    let httpParams: HttpParams = undefined;
    if (req !== undefined) {
      httpParams = new HttpParams();
      Object.keys(req).forEach(key => {
        const value = req[key];
        console.log(`key: ${key}, value: ${value}`);
        if (value !== undefined) {
          httpParams = httpParams.append(key, req[key]);
        }
      });
    }
    console.log(`httpParams: ${httpParams}`);
    return httpParams;
  }

  private makeData(req: any) {
    let formData: FormData = new FormData();
    Object.keys(req).forEach(key => {
      const value = req[key];
      console.log(`key: ${key}, value: ${value}`);
      if (value !== undefined) {
        if (value instanceof Array && (value as any[])[0] instanceof File) {
          let files: File[] = (value as File[]);
          for (let file of files) {
            formData.append(key, file, file.name);
          }
        } else if (value instanceof File) {
          let file: File = value;
          formData.append(key, file, file.name);
        } else {
          formData.append(key, value);
        }
      }
    });
    console.log(`formData: ${formData}`);
    return formData;
  }

  private undefined2Null(req: any) {
    if (req === undefined)  return undefined;
    Object.keys(req).forEach(name => {
      if (req[name] === undefined) {  // 'undefined'로 전송되어서...
        req[name] = '';
      }
    });
    return req;
  }
}
