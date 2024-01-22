import { NgModule } from '@angular/core';
import { NumLoopPipe } from './num-loop.pipe';
import { NumPadPipe } from './num-pad.pipe';

@NgModule({
  declarations: [
    NumLoopPipe,
    NumPadPipe
  ],
  exports: [
    NumLoopPipe,
    NumPadPipe
  ]
})
export class PipeModule { }
