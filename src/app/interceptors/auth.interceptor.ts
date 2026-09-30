import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // TODO: Implement authentication logic here (e.g., attach tokens)
  return next(req);
};
