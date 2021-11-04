package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoEntity;

import lombok.extern.slf4j.Slf4j;
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	//?1은 메서드의 매개변수의 순서 위치다.
	@Query("select * fromn Todo t where t.userid = ?1")
	List<TodoEntity> findByUserId(String userId);
	
	
	
}
