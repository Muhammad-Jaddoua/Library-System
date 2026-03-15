import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  private authService = inject(AuthService);
  private router = inject(Router);

  navigateToBooks(){
    this.router.navigate(['books'])

  }
  registerForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  registerRequest(){
    this.authService.register(
      this.registerForm.value.username?? '',
      this.registerForm.value.password?? '',
    ).subscribe({
      next: (response) => {
        this.authService.setToken(response.token);
        this.navigateToBooks();
        },

      error: (err) => {alert("Register Failed, please check your credentials.")},
    });
  }
}
