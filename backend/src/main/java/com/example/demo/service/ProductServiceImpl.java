package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mallapi.domain.Product;
import com.example.demo.mallapi.domain.ProductImage;
import com.example.demo.mallapi.dto.PageRequestDTO;
import com.example.demo.mallapi.dto.PageResponseDTO;
import com.example.demo.mallapi.dto.ProductDTO;
import com.example.demo.mallapi.repository.ProductRepository;
import com.example.demo.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CustomFileUtil fileUtil;

	@Override
	public void remove(Long pno) {
		// 1. 기존 상품 조회
		Product product = productRepository.selectOne(pno).orElseThrow();

		// 기존 파일명 목록 저장
		List<String> oldFileNames = product.getImageList().stream().map(productImage -> productImage.getFileName())
				.collect(Collectors.toList());

		// soft delete 처리
		productRepository.updateToDelete(pno, true);

		// 실제 파일 삭제
		fileUtil.deleteFiles(oldFileNames);
	}

	// 수정
	@Override
	public void modify(ProductDTO productDTO) {
		// 1. 기존 상품 조회
		Optional<Product> result = productRepository.findById(productDTO.getPno());
		Product product = result.orElseThrow();

		// 2. 기본 정보 수정
		product.changeName(productDTO.getPname());
		product.changeDesc(productDTO.getPdesc());
		product.changePrice(productDTO.getPrice());

		// 업로드 파일 정보들
		product.clearList();

		// 3. 새 파일 저장
		List<String> uploadFileNames = productDTO.getUploadFileNames();

		// 4. 새 파일이 있을 때만 이미지 교체
		if (uploadFileNames != null && uploadFileNames.size() > 0) {
			uploadFileNames.forEach(product::addImageString);
		}

		// 5. 저장
		productRepository.save(product);

	}

	// 등록
	@Override
	public Long register(ProductDTO productDTO) {

		List<String> uploadFileNames = fileUtil.saveFiles(productDTO.getFiles());
		productDTO.setUploadFileNames(uploadFileNames);

		Product product = dtoToEntity(productDTO);
		Product result = productRepository.save(product);

		return result.getPno();
	}

	// 상세 조회
	@Override
	public ProductDTO get(Long pno) {
		Product product = productRepository.selectOne(pno).orElseThrow();

		return entityToDto(product);
	}

	// 목록 조회
	@Override
	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
		log.info("getList call..............");
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
				Sort.by("pno").descending());

		Page<Object[]> dtoList = productRepository.selectList(pageable);

		List<ProductDTO> result = dtoList.getContent().stream().map(objArr -> {
			Product product = (Product) objArr[0];
			ProductImage productImage = (ProductImage) objArr[1];

			ProductDTO productDTO = ProductDTO.builder().pno(product.getPno()).pname(product.getPname())
					.pdesc(product.getPdesc()).price(product.getPrice()).build();

			if (productImage != null) {
				String imageStr = productImage.getFileName();
				productDTO.setUploadFileNames(List.of(imageStr));
			}
			return productDTO;
		}).collect(Collectors.toList());

		long totalCount = dtoList.getTotalElements();

		return PageResponseDTO.<ProductDTO>withAll().dtoList(result).totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO).build();

	}

	private Product dtoToEntity(ProductDTO productDTO) {
		Product product = Product.builder().pname(productDTO.getPname()).pdesc(productDTO.getPdesc())
				.price(productDTO.getPrice()).build();

		// 업로드 처리가 끝난 파일들의 이름 리스트 처리
		List<String> uploadFileNames = productDTO.getUploadFileNames();

		if (uploadFileNames != null) {
			uploadFileNames.forEach(product::addImageString);
		}

		return product;
	}

	private ProductDTO entityToDto(Product product) {
		ProductDTO productDTO = ProductDTO.builder().pno(product.getPno()).pname(product.getPname())
				.pdesc(product.getPdesc()).price(product.getPrice()).delFlag(product.isDelFlag()).build();
		List<ProductImage> imageList = product.getImageList();

		if (imageList == null || imageList.size() == 0)
			return productDTO;

		List<String> fileNameList = imageList.stream().map(productImage -> productImage.getFileName()).toList();
		productDTO.setUploadFileNames(fileNameList);

		return productDTO;

	}
}