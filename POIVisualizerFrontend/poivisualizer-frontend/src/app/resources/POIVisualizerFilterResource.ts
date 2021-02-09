import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import AbstractResource from "./AbstractResource";
import POIResultDTO from './dto/POIResultDTO';
import POIVisualizerFilterDTO from './dto/POIVisualizerFilterDTO';

@Injectable({
    providedIn: 'root'
})

export default class POIVisualizerFilterResource extends AbstractResource {

    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    public queryByFilterDTO(filter: POIVisualizerFilterDTO, callback: (r: POIResultDTO) => void) : void {
        
        
        const FIND_ALL_ENDPOINT = "/poivisualizer/getbyvehicleanddate";

        this.POST(FIND_ALL_ENDPOINT, filter).subscribe((data: POIResultDTO) => {
            callback(data);
        })
    }
}