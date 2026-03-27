package com.example.demo.repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mallapi.domain.Product;
import com.example.demo.mallapi.domain.ProductImage;
import com.example.demo.mallapi.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

	@Autowired
	ProductRepository productRepository;
	
//	@Test
	public void testList() {
		Pageable pageable = PageRequest.of(0, 10,Sort.by("pno").descending()); // 1페이지(0부터 시작)
		Page<Object[]> result = productRepository.selectList(pageable);
		
		result.getContent().forEach(arr -> {
			log.info(Arrays.toString(arr));
		});
	}
	
//	@Test
	public void testUpdate() {
		Long pno = 10L;
		
		Product product = productRepository.selectOne(pno).get();
		
		product.changeName("10번 상품");
		product.changeDesc("10번 상품에 대한 설명");
		product.changePrice(5000);
		
		// 첨부 파일 수정
		// JPA는 이걸 삭제로 인식
		product.clearList();
		
		product.addImageString(UUID.randomUUID().toString()+"_"+"NEWIMAGE1.jpg");
		product.addImageString(UUID.randomUUID().toString()+"_"+"NEWIMAGE2.jpg");
		product.addImageString(UUID.randomUUID().toString()+"_"+"NEWIMAGE3.jpg");
		
		// 결과 
		// 기존 이미지는 전부 삭제, 새 이미지 3개 새로 추가
		productRepository.save(product);
	}
	
	//수정
//	@Test
	@Commit
	@Transactional
	public void testDelete() {
		Long pno = 2L;
		
		productRepository.updateToDelete(pno, true);
	}
	
	
	// 조회
//	@Test
	public void testRead() {
		Long pno = 1L;
		Optional<Product> result = productRepository.selectOne(pno);
		
		Product product = result.orElseThrow();
		
		log.info(product);
		log.info(product.getImageList());
	}
	
	
	@Test
	public void testInsert() {
		for(int i = 0;i<10;i++) {
			Product product = Product.builder()
					.pname("상품" + i)
					.price(100*i)
					.pdesc("상품설명..." + i)
					.build();
			
			// 2개의 이미지 파일 추가하기 
			product.addImageString(UUID.randomUUID().toString() + 
					"_" + "IMAGE1.jpg");
			
			product.addImageString(UUID.randomUUID().toString() + 
					"_" + "IMAGE2.jpg");
			
			productRepository.save(product);
			
			log.info("=====================");
		}
	}
	
	
}
