package com.example.demo.mallapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TodoDTO {

	private Long tno;
	private String title;
	private String writer;
	private boolean complete;
	
	// 아래 데이터는 리엑트에서 쉽게 처리하기 위해 JSON 포맷으로 변경함
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
	
}