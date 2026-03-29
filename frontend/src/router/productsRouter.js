import { lazy, Suspense } from 'react';

const Loading = <div>Loding...</div>;
const Add = lazy(() => import('../pages/products/AddPage'));
const Read = lazy(() => import('../pages/products/ReadPage'));
const List = lazy(() => import('../pages/products/ListPage'));

// children 안에서는 / 빼야 부모 경로(/products)가 자동으로 붙는다
const productsRouter = () => [
  {
    path: '',
    element: (
      <Suspense fallback={Loading}>
        <List />
      </Suspense>
    ),
  },
  {
    path: 'list', // 부모 기준 상대 경로
    element: (
      <Suspense fallback={Loading}>
        <List />
      </Suspense>
    ),
  },
  {
    path: 'read',
    element: (
      <Suspense fallback={Loading}>
        <Read />
      </Suspense>
    ),
  },
  {
    path: 'add',
    element: (
      <Suspense fallback={Loading}>
        <Add />
      </Suspense>
    ),
  },
];

export default productsRouter;
