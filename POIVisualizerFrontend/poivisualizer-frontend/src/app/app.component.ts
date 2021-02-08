import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'poivisualizer-frontend';
  cars = [
    {
      placa:"paranaense",
      tempo:"so deus dira"
    }
  ]
}
