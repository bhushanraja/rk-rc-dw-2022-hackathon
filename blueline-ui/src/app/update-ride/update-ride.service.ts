import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fare } from '../book-ride/fare';
import { Rides } from './rides';

@Injectable({
  providedIn: 'root'
})
export class UpdateRideService {

  constructor(private http:HttpClient) { }
  getRides(updateRideData: any) :Observable<Rides[]>{
    return this.http.get<Rides[]>("http://bluelineapi-env.eba-gjgvptkm.us-east-1.elasticbeanstalk.com/bookings/"+updateRideData);
  }
  getFares() :Observable<Fare[]>{
    return this.http.get<Fare[]>("http://bluelineapi-env.eba-gjgvptkm.us-east-1.elasticbeanstalk.com/bookings/fares");
  }
  updateRide(bookingId:any,updateRideData:any){
    return this.http.put<any>("http://bluelineapi-env.eba-gjgvptkm.us-east-1.elasticbeanstalk.com/bookings/"+bookingId,updateRideData);
  }
}
