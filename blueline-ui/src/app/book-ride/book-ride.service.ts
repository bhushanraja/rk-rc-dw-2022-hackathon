import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Fare } from './fare';

@Injectable({
  providedIn: 'root'
})
export class BookRideService {

  constructor(private http:HttpClient) { }

  getFares() :Observable<Fare[]>{
    return this.http.get<Fare[]>("http://bluelineapi-env.eba-gjgvptkm.us-east-1.elasticbeanstalk.com:5000//bookings/fares");
  }
  bookRide(bookRideData: any): Observable<any>{
    return this.http.post<string>("http://bluelineapi-env.eba-gjgvptkm.us-east-1.elasticbeanstalk.com:5000/bookings/book-taxi",bookRideData);
  }

}
