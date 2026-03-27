package com.example.demo.service;

import com.example.demo.mallapi.dto.PageRequestDTO;
import com.example.demo.mallapi.dto.PageResponseDTO;
import com.example.demo.mallapi.dto.TodoDTO;

public interface TodoService {
	
	Long register(TodoDTO todoDTO);
	
	TodoDTO get(Long tno);
	
	void modify(TodoDTO todoDTO);
	
	void remove(Long tno);
	
	PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);

}