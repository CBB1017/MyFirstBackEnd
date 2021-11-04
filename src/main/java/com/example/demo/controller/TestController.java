package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import com.example.demo.service.TodoService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("test")//���ҽ� ��� ����
public class TestController {

	@GetMapping("/GetMapping")//GET �޼��� ���� �� �������� �� �˾Ƽ� ���ش� �������;������..
	public String testController() {
		return "Hello World! testGetMapping";
	}
	
	@GetMapping("/RequestBody")//GET �޼��� ���� �� �������� �� �˾Ƽ� ���ش� �������;������..
	public String testRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID "+ testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/ResponseBody")
	public ResponseDTO<String> testControllerResponseBody(){
		List<String> list = new ArrayList<>();
		list.add("Hello world! im repsonsedto");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	
	@GetMapping("/ResponseBodyEntity")
	public ResponseEntity<?> testControllerResponseBodyEntity(){
		List<String> list = new ArrayList<>();
		list.add("Hello world! im responseEntity. you got 400");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
	}
	 
}
