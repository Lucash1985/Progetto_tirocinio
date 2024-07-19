import { Component } from '@angular/core';
import { AxiosService } from '../axios.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html'
})
export class FileUploadComponent {
  maxFileSize = 10 * 1024 * 1024; // 10 MB
  errorMessage: string = '';

  constructor(private axiosService: AxiosService) {}

  async uploadFile(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      if (file.size > this.maxFileSize) {
        this.errorMessage = 'The selected file exceeds the maximum size of 10 MB.';
      } else {
        this.errorMessage = '';

        try {
          const formData = new FormData();
          formData.append('file', file);

          const response = await this.axiosService.requestFile('POST', '/api/upload', formData);
          console.log('File uploaded successfully', response);
        } catch (error) {
          console.error('Error uploading file', error);
          this.errorMessage = 'Error uploading file';
        }
      }
    }
  }
}
