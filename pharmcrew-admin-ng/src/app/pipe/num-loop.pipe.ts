import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numLoop'
})
export class NumLoopPipe implements PipeTransform {

  transform(value: number, ...args: number[]): number[] {
    let init: number = 0;
    let step: number = 1;
    if (args.length > 0) {
      step = args[0];
      if (args.length > 1) {
        init = args[1];
      }
    }
    // console.log(`value: ${value}, init: ${init}, step: ${step}`);

    let result = [];
    for (let i = init; i < (value + value % step); i += step) {
      result.push(i);
    }
    // console.log(`result: ${result}`);
    return result;
  }

}
