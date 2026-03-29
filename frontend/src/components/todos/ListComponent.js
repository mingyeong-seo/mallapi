import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
// import { host, getList } from '../../api/todosApi'

const ListComponent = () => {
  // 1. state
  // 정의해야 할 상태를 작성 (page, size는 아직 구현 안 함)
  // [현재 상태값, 그 상태를 바꾸는 함수]
  const [todos, setTodos] = useState([]); //[현재 todo 목록 데이터, 그 목록 데이터를 바꾸는 함수]

  const navigate = useNavigate();

  // 더미 데이터 정의
  const testData = [
    { tno: 1, title: '할 일 1', writer: '이용자 1' },
    { tno: 2, title: '할 일 2', writer: '이용자 2' },
    { tno: 3, title: '할 일 3', writer: '이용자 3' },
  ];

  // 2. useEffect
  // useEffect(() => { ... }, []) 이유는 처음 렌더링 끝난 뒤 한번만 실행하라는 의미이다.
  // 화살표 함수는 "지금 실행하는 게 아니라, 나중에 실행할 함수를 전달"할 때 쓴다
  useEffect(() => {
    setTodos(testData);
  }, []);

  // 3. map
  return (
    <>
      {todos.map((todo) => (
        <div
          key={todo.tno}
          onClick={() => {
            navigate(`/todos/read/${todo.tno}`);
          }}
        >
          {todo.title}
        </div>
      ))}
    </>
  );
};

export default ListComponent;
