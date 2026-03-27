package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mallapi.dto.PageRequestDTO;
import com.example.demo.mallapi.dto.PageResponseDTO;
import com.example.demo.mallapi.dto.TodoDTO;
import com.example.demo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {

	private final TodoServiceImpl todoService;

	// {tno} 패스로 요청이 오면, 해당 번호의 글을 가져오도록 메서드를 작성하시오
	@GetMapping("/{tno}")
	public TodoDTO read(@PathVariable(name = "tno") Long tno) {
		return todoService.get(tno);
	}

	@GetMapping("/list")
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
		log.info(pageRequestDTO);

		return todoService.list(pageRequestDTO);
	}

	@PostMapping("/")
	public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
		log.info("TodoDTO: " + todoDTO);

		Long tno = todoService.register(todoDTO);
		return Map.of("tno", tno);
	}

	@PutMapping("/{tno}")
	public Map<String, String> modify(@PathVariable(name = "tno") Long tno, @RequestBody TodoDTO todoDTO) {

		todoDTO.setTno(tno);
		log.info("수정: " + todoDTO);
		todoService.modify(todoDTO);

		return Map.of("RESULT", "SUCCESS");

	}

	@DeleteMapping("/{tno}")
	public Map<String, String> remove(@PathVariable(name = "tno") Long tno) {
		log.info("삭제 번호: " + tno);
		todoService.remove(tno);

		return Map.of("RESULT", "SUCCESS");
	}

}
