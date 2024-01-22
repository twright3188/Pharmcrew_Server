import { Component, OnInit } from '@angular/core';

declare var $ :any;

@Component({
  selector: 'layout-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  onGroupClick(event: Event) {
	var $this = $(event.target);
    var element = $this.parent('li');
    if (element.hasClass('open')) {
		element.removeClass('open');
		element.find('li').removeClass('open');
		element.find('ul').removeClass('dsp_b');
		element.find('ul').slideUp();
	}
	else {
		// element.addClass('open');
		element.children('ul').slideDown();
		element.siblings('li').children('ul').slideUp();
		element.siblings('li').children('ul').removeClass('dsp_b');
		element.siblings('li').removeClass('open');
		element.siblings('li').find('li').removeClass('open');
		element.siblings('li').find('ul').slideUp();
	}
  }

  authority() {
	  return localStorage.getItem('authority');
  }

}
