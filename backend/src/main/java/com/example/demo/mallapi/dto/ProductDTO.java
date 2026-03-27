package com.example.demo.mallapi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long pno;
	private String pname;
	private int price;
	private String pdesc;
	private boolean delFlag;
	
	@Builder.Default
	private List<MultipartFile> files = new ArrayList<>(); // 사용자가 업로드한 실제 파일(request에서 넘어옴)
	@Builder.Default  // builder로 객체 생성해도 기본값 유지해라
	private List<String> uploadFileNames = new ArrayList<>(); // 서버에 저장된 파일 이름(DB 저장용)

}
