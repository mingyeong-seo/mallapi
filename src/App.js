import { RouterProvider } from 'react-router-dom';
import root from './router/root';

// 라우터만 실행만 하는 역할
function App() {
  return <RouterProvider router={root} />;
}

export default App;
