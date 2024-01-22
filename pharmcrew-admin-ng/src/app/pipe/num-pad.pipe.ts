import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numPad'
})
export class NumPadPipe implements PipeTransform {

  transform(value: number, ...args: number[]): unknown {
    let num = value + "";
    const width = args[0];
    return num.length >= width ? num : new Array(width - num.length + 1).join('0') + num;
  }

}
