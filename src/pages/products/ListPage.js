import { Link } from 'react-router-dom';
import ListComponent from '../../components/products/ListComponent'; // import .. from ..

const ListPage = () => {
  return (
    /* return 안에는 하나의 부모 요소만 있어야 한다 */
    <div>
      <h1>List Page</h1>
      <Link to="/products/add">등록하기</Link>
      <ListComponent />
    </div>
  );
};

export default ListPage;
