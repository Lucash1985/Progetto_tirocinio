import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    axios.defaults.baseURL = 'http://localhost:8080';
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }
   requestFile(method: string, url: string, data: any ): Promise<any> {
    
  let headers = {}
    // Aggiungi il token di autenticazione, se presente
    const token = this.getAuthToken();
    if (token !== null) {
     headers = {
                   "Authorizaton" : "Bearer "+token,
                   "Content-Type": "multipart/form-data"

      };
    }
 // Imposta 'Content-Type' solo se 'data' non è una istanza di FormData
 
     
      return axios({
        method: method,
        url: url,
        data: data,
        headers: headers
       
    
      });
      
  
  }

  async request(method: string, url: string, data: any = null): Promise<any> {
    const headers: any = {};

    // Aggiungi il token di autenticazione, se presente
    const token = this.getAuthToken();
    if (token !== null) {
      headers['Authorization'] = "Bearer " + token;
    }
 // Imposta 'Content-Type' solo se 'data' non è una istanza di FormData
 if (!(data instanceof FormData)) {
  headers['Content-Type'] = 'application/json';
}
    try {
      const response = await axios({
        method: method,
        url: url,
        data: data,
        headers: headers
       
    
      });
      return response.data;
    } catch (error) {
      console.error('Error in request:', error);
      throw error;
    }
  }
}
