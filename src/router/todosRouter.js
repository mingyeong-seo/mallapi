import { lazy, Suspense } from 'react';

const Loading = <div>Loading...</div>;
const List = lazy(() => import('../pages/todos/ListPage'));
const Read = lazy(() => import('../pages/todos/ReadPage'));
const Add = lazy(() => import('../pages/todos/AddPage'));

const todoRouter = () => [
  {
    path: '',
    element: (
      <Suspense fallback={Loading}>
        <List />
      </Suspense>
    ),
  },
  {
    path: 'list',
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

export default todoRouter;
