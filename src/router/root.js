import { createBrowserRouter } from 'react-router-dom';
import productsRouter from './productsRouter';
import { lazy, Suspense } from 'react';

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
]);

export default root;
