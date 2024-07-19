import { Component } from '@angular/core';
import { AxiosService } from '../axios.service';
import * as bcrypt from 'bcryptjs';
import { AuthContentComponent } from '../auth-content/auth-content.component';
@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
  componentToShow: string = "welcome";
  errorMessage: string = '';
  constructor(private axiosService: AxiosService) {}

  showComponent(componentToShow: string): void {
    this.componentToShow = componentToShow;
  }

  hashPassword(password: string): string {
    const salt = bcrypt.genSaltSync(10);
    return bcrypt.hashSync(password, salt);
  }

  onLogin(input: any): void {
    if (!input.login || !input.password) {
      this.errorMessage = 'Please enter both login and password.';
      return;
    }

    console.log('Attempting to login with:', input);

    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password
      }).then(
      response => {
        console.log('Login successful:', response);
        this.axiosService.setAuthToken(response.token);
        this.errorMessage = '';
        this.componentToShow = "messages";
      }).catch(
      error => {
        console.error('Login error:', error);
        this.axiosService.setAuthToken(null);
        if (error.response && error.response.data && error.response.data.message) {
          this.errorMessage = error.response.data.message;
        } else {
          this.errorMessage = 'Login failed. Please try again.';
        }
        this.componentToShow = "welcome";
      }
    );
  }

  onRegister(input: any): void {
    // Hashare la password prima di inviarla al server
    const hashedPassword = this.hashPassword(input.password);
    this.axiosService.request(
      "POST",
      "/register",
      {
        firstName: input.firstName,
        lastName: input.lastName,
        login: input.login,
        password: hashedPassword
      }).then(
      response => {
        this.axiosService.setAuthToken(response.data.token);
        this.componentToShow = "messages";
      }).catch(
      error => {
        this.axiosService.setAuthToken(null);
        this.componentToShow = "welcome";
      }
    );

  }
  async uploadFile(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      

        try {
          const formData = new FormData();
          formData.append('file', file);

          const response = await this.axiosService.requestFile('POST', '/api/upload', formData);
          this.componentToShow = "upload";
          console.log('File uploaded successfully', response);
        } catch (error) {
          console.error('Error uploading file', error);
          this.componentToShow = "welcome";
          this.errorMessage = 'Error uploading file';
        }
      }
    }
  }

