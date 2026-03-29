import axios from 'axios';

export const API_SERVER_HOST = 'http://localhost'; // 서버 기본 주소

const host = `${API_SERVER_HOST}/api/products`; // products API 전용 prefix

// 실제 API 함수 -> 외부에서 호출하는 함수
// 상품 목록 조회
export const getList = async (pageParam) => {
  const { page, size } = pageParam; // 페이지 정보 분리

  // axios 요청
  // URL: /api/products/list
  // params: query string 자동 생성(실제 요청: /api/products/lsit?page=1&size=10)
  const res = await axios.get(`${host}/list`, {
    params: { page, size },
  });

  // 데이터 반환 -> axios는 data안에 실제 값 있음
  return res.data;
};
