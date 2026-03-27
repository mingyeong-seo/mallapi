package com.example.demo.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.mallapi.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}