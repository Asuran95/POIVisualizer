import VehiclePoiDTO from "./VehiclePoiDTO";

export default class POIResultDTO{
    public name : string;
    public raio : number;
    public longitude : number;
    public latitude : number;
    public vehicles : VehiclePoiDTO[]; 


}