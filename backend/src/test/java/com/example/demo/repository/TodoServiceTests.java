package com.example.demo.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mallapi.dto.PageRequestDTO;
import com.example.demo.mallapi.dto.PageResponseDTO;
import com.example.demo.mallapi.dto.TodoDTO;
import com.example.demo.service.TodoService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2

public class TodoServiceTests {

	@Autowired
	private TodoService todoService;
	
	// @Test
	public void testRegister() {
		TodoDTO dto = TodoDTO.builder()
				.title("서비스 테스트")
				.writer("tester")
				.dueDate(LocalDate.of(2026, 3, 31))
				.build();
		
		Long tno = todoService.register(dto);
		log.info("TNO : " + tno);
	}
	
	// @Test
	public void testGet() {
		Long tno = 101L;
		
		TodoDTO todoDTO = todoService.get(tno);
		
		log.info(todoDTO);
	}
	
	// @Test
	public void testModify() {
	    TodoDTO dto = TodoDTO.builder()
	            .tno(101L)
	            .title("수정된 제목")
	            .complete(true)
	            .dueDate(java.time.LocalDate.now())
	            .build();

	    todoService.modify(dto);
	}
	
	// @Test
	public void testRemove() {
	    Long tno = 101L;

	    todoService.remove(tno);
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.page(2)
				.size(10)
				.build();
		
		PageResponseDTO<TodoDTO> responseDTO = todoService.list(pageRequestDTO);
		
		log.info(responseDTO);
	}
	
}