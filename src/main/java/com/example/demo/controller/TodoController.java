package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			//TOdoEntity로 변환
			TodoEntity entity = TodoDTO.toEntity(dto);
			//id null로 초기화 후
			entity.setId(null);
			//임시 사용자 아이디 설정. 4장 인증과 인가에서 수정할 예정
			entity.setUserId(temporaryUserId);
			//서비스를 이용해 TOdo 엔티티 생성
			List<TodoEntity> entities = service.create(entity);
			//자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			//변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
//			DTO 리턴
			return ResponseEntity.ok().body(response);
 		}catch (Exception e) {
 			String error = e.getMessage();
 			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
 			return ResponseEntity.badRequest().body(response);
 		}
	}
	@GetMapping()
	public  ResponseEntity<?> retrieveTodoList(){
		String temporaryUserId = "temporary-user";
		//서비스 메서드의 retrieve()메서드를 사용해 Todo 리스트를 가져온다
		List<TodoEntity> entities = service.retrieve(temporaryUserId);
		//자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		//변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		//REsponseDTO 리턴
		return  ResponseEntity.ok().body(response);
	}
	
}
