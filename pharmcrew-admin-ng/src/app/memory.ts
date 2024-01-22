// // [REFERENCE] https://stackoverflow.com/questions/30174078/how-to-define-singleton-in-typescript
// export class Memory {
//     private static _instance: Memory;

//     // private cookie: string;
//     // private adminId: number;
//     private name: string;

//     private constructor() { }

//     public static get Inst() {
//         return this._instance || (this._instance = new this());
//     }

//     // cross origin 환경에서 HttpOnly 방식의 cookie는 스크립트에서 접근이 불가능
//     // public getCookie() {
//     //     return this.cookie;
//     // }
//     // public setCookie(cookie: string) {
//     //     this.cookie = cookie;
//     // }
    
//     // public getAdminId() {
//     //     return this.adminId;
//     // }
//     // public setAdminId(adminId: number) {
//     //     this.adminId = adminId;
//     // }
//     public getName() {
//         // return this.name;
//         return localStorage.getItem('name');
//     }
//     public setName(name: string) {
//         // this.name = name;
//         return localStorage.setItem('name', name);
//     }
// }