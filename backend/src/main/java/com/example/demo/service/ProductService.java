package com.example.demo.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mallapi.dto.PageRequestDTO;
import com.example.demo.mallapi.dto.PageResponseDTO;
import com.example.demo.mallapi.dto.ProductDTO;

@Transactional
public interface ProductService {

	PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

	// 등록
	Long register(ProductDTO productDTO);
	
	// 상세 조회
	ProductDTO get(Long pno);

	// 수정
	void modify(ProductDTO productDTO);

	// 삭제
	void remove(Long pno);

}
