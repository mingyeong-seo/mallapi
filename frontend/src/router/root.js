import { createBrowserRouter } from 'react-router-dom';
import productsRouter from './productsRouter';
import { lazy, Suspense } from 'react';
import todoRouter from './todosRouter';

const Loading = <div>Loading...</div>;
const Main = lazy(() => import('../pages/MainPage'));

// React Router는 이렇게 합친다. (부모 path + 자식 path)
const root = createBrowserRouter([
  {
    path: '/', // 절대 경로(루트 기준)
    element: (
      <Suspense fallback={Loading}>
        <Main />
      </Suspense>
    ),
  },
  {
    path: '/products', // 절대 경로(루트 기준)
    children: productsRouter(),
  },
  {
    path: '/todos',
    children: todoRouter(),
  },
]);

export default root;
