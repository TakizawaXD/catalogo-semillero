import { HttpInterceptorFn } from '@angular/common/http';

export const errorAuthInterceptor: HttpInterceptorFn = (req, next) => {
  // TODO: Implement error handling logic here (e.g., redirect on 401)
  return next(req);
};
