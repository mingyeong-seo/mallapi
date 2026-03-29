import { useEffect, useState } from 'react';
import { getList } from '../../api/productsApi';
import { useNavigate } from 'react-router-dom';

const ListComponent = () => {
  // state
  const [products, setProducts] = useState([]); // [화면에 뿌릴 데이터, 서버 데이터 넣는 함수]
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(10);

  const navigate = useNavigate();

  // useEffect
  useEffect(() => {
    getList({ page, size }).then((data) => {
      console.log(data);
      setProducts(data.dtoList); 
    });
  }, [page, size]);

  // return + map
  return (
    <div>
      {products.map((product) => (
        <div
          key={product.pno}
          onClick={() => navigate(`/products/read/${product.pno}`)}
        >
           상품명 : {product.pname}
        </div>
      ))}
    </div>
  );
};

export default ListComponent;
