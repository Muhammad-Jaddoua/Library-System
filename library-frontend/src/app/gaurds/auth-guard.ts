import {ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot, RedirectCommand, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  if (authService.isLoggedIn()){
    return true;
  } else {
    return router.createUrlTree(['login'])
  }
};
