import { Component } from '@angular/core';
import POIDTO from './resources/dto/POIDTO';
import POIResultDTO from './resources/dto/POIResultDTO';
import POIVisualizerFilterDTO from './resources/dto/POIVisualizerFilterDTO';
import VehiclePoiDTO from './resources/dto/VehiclePoiDTO';
import POIResource from './resources/POIResource'
import POIVisualizerFilterResource from './resources/POIVisualizerFilterResource';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  public pois : POIDTO[];

  public selectedPoi : POIDTO;
  public placa : string;
  public dataInicial : Date;
  public dataFinal : Date;
  public veiculos : VehiclePoiDTO[];

  constructor(private poiResource : POIResource,
              private poiVisualizerResource : POIVisualizerFilterResource){
  }

  public ngOnInit() : void {
    this.poiResource.findAll(this.handleServiceInitFindAllPoi.bind(this), null);
  }

  private handleServiceInitFindAllPoi(poiDtos : POIDTO[]){
    console.log(poiDtos);

    this.pois = Array.from(poiDtos);
    
    if(this.pois.length != 0){
      this.selectedPoi = this.pois[0];
    }
  }

  private handleFindAllByFilter(poiResultDto : POIResultDTO){
    console.log(poiResultDto);

    this.veiculos = Array.from(poiResultDto.vehicles);
  }

  public consultar() : void {
    
    let filter = new POIVisualizerFilterDTO();

    filter.poidto = this.selectedPoi;
    filter.placa = this.placa;
    filter.dataInicial = this.dataInicial;
    filter.dataFinal = this.dataFinal;

    this.poiVisualizerResource.queryByFilterDTO(filter, this.handleFindAllByFilter.bind(this));  
  }
}
