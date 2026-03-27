package com.example.demo.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mallapi.domain.Todo;
import com.example.demo.mallapi.repository.TodoRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2

public class TodoRepositoryTests {

	@Autowired
	private TodoRepository todoRepository;

	//@Test
	public void testInsert() {
		for (int i = 1; i <= 100; i++) {

			Todo todo = Todo.builder().title("Title..." + i).dueDate(LocalDate.of(2023, 12, 31)).writer("user00")
					.build();

			todoRepository.save(todo);
		}
	}

	// @Test
	public void testRead() {
		Long tno = 33L;

		java.util.Optional<Todo> result = todoRepository.findById(tno);

		Todo todo = result.orElseThrow();

		log.info(todo);
	}

	// @Test
	public void testModify() {
		Long tno = 33L;

		java.util.Optional<Todo> result = todoRepository.findById(tno); // java.util 패키지의 Optional

		Todo todo = result.orElseThrow();
		todo.changeTitle("Modified 33...");
		todo.changeComplete(true);
		todo.changeDueDate(LocalDate.of(2023, 10, 10));

		todoRepository.save(todo);
	}

	// @Test
	public void testDelete() {
		Long tno = 1L;

		todoRepository.deleteById(tno);
	}

}