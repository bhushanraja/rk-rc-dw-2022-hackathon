import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rides } from './rides';

@Injectable({
  providedIn: 'root'
})
export class CancelRideService {

  constructor(private http:HttpClient) { }
  getRides(mobileNo: any) :Observable<Rides[]>{
    return this.http.get<Rides[]>("http://bluelineapi-env.eba-gjgvptkm.us-east-1.elasticbeanstalk.com:5000/bookings/"+mobileNo);
  }
  cancelRide(bookingId: any){
    return this.http.delete<any>("http://bluelineapi-env.eba-gjgvptkm.us-east-1.elasticbeanstalk.com:5000/bookings/"+bookingId);
  }
}
