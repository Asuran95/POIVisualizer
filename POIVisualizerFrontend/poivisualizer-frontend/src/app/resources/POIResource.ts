import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

import AbstractResource from "./AbstractResource";
import POIDTO from "./dto/POIDTO";

@Injectable({
    providedIn: 'root'
})

export default class POIResource extends AbstractResource {

    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    public findAll(callback: (r: POIDTO[]) => void,
        errHandler: (r: HttpErrorResponse) => void): void {

        const FIND_ALL_ENDPOINT = "poivisualizer/getpois";

        this.GET(FIND_ALL_ENDPOINT)
            .subscribe((data: POIDTO[]) => {
                callback(data);
            },
                errHandler
            );
    }
}