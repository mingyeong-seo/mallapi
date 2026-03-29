import { Link } from 'react-router-dom';

const MainPage = () => {
  return (
    <div>
      <h1>Hello Main Page!</h1>
      <Link to="/products/list">리스트로 이동</Link>
    </div>
  );
};

export default MainPage;
/* 화면에서 보여주는 이동은 Link, 로직 안에서 이동은 useNavigate */
