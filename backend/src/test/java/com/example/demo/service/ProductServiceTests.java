package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mallapi.dto.PageRequestDTO;
import com.example.demo.mallapi.dto.PageResponseDTO;
import com.example.demo.mallapi.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTests {

	@Autowired
	private ProductService productService;

	// 삭제
//	@Test
	public void testRemove() {

	    Long pno = 13L;

	    productService.remove(pno);

	    log.info("삭제 처리 완료: " + pno);
	}
	
	// 수정
//	@Test
	public void testModify() {
		Long pno = 10L;

		// 1. 기존 상품 조회
		ProductDTO before = productService.get(pno);
		log.info("수정 전: " + before);

		// 2. 수정용 DTO 준비
		ProductDTO productDTO = ProductDTO.builder().pno(pno).pname("수정된 상품명").pdesc("수정된 상품 설명").price(9999).build();

		// 3. 수정 실행
		productService.modify(productDTO);

		// 4. 수정 후 다시 조회
		ProductDTO after = productService.get(pno);

		log.info("수정 후: " + after);
		log.info("수정 후 이미지 목록: " + after.getUploadFileNames());
	}

	// 등록
//	@Test
	public void testRegister() {
		 ProductDTO productDTO = ProductDTO.builder()
	                .pname("새로운 상품")
	                .pdesc("새로운 상품 추가 내용임,,")
	                .price(100000)
	                .build();
		 // UUID 생성 및 image name 
		 productDTO.setUploadFileNames(
				 List.of(
						UUID.randomUUID()+"_"+"Test1.jpg",
				 		UUID.randomUUID()+"_"+"Test2.jpg"
				 )
			);
		 productService.register(productDTO);
	}

	// 상세 조회
	@Test
	public void testRead() {
		Long pno = 10L;

		ProductDTO productDTO = productService.get(pno);

		log.info(productDTO);
		log.info(productDTO.getUploadFileNames());
	}

	// 목록 조회
//	@Test
	public void testGetList() {

		// 1. 페이지 요청 DTO 생성
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

		// 2. 서비스 호출
		PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);

		// 3. 전체 정보 먼저 확인
		log.info("current: " + result.getCurrent());
		log.info("totalCount: " + result.getTotalCount());
		log.info("totalPage: " + result.getTotalPage());
		log.info("dtoList size: " + result.getDtoList().size());

		// 4. 실제 DTO 확인
		result.getDtoList().forEach(dto -> {
			log.info("----------------------");
			log.info(dto);
		});
	}
}
