import {Component, inject, output} from '@angular/core';
import {ReactiveFormsModule, FormGroup, Validators, FormControl} from '@angular/forms';
import {AuthService} from '../../services/auth';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private authService = inject(AuthService);
  private router = inject(Router);

  navigateToBooks(){
    this.router.navigate(['books'])
  }

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  loginRequest(){
      this.authService.login(

      this.loginForm.value.username ?? '',
      this.loginForm.value.password ?? '').subscribe({
        next: (response) => {
          this.authService.setToken(response.token);
          this.navigateToBooks(); },

        error: (err) => {alert("Login Failed, please check your credentials.")},
      });
  }
}
