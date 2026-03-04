import {Routes} from '@angular/router';
import {Login} from './pages/login/login';
import {Register} from './pages/register/register';
import {Books} from '././pages/books/books';
import {NotFound} from './pages/not-found/not-found';

export const routes: Routes = [
  {path: 'login',
    title: 'login page',
    component: Login,
  },
  {path: 'register',
    title: 'register page',
    component: Register,
  },
  {path: 'books',
    title: 'Book system',
    component: Books,
  },{path: '**',
    title: 'Page not found',
    component: NotFound},
];
