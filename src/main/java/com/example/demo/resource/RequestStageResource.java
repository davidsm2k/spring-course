package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.RequestStage;
import com.example.demo.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageResource {

	@Autowired private RequestStageService requestStageService;
	
	//save
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody RequestStage stage){
		RequestStage createdRequestStage = requestStageService.save(stage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
	}
	
	//getbyid
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id){
		RequestStage stage = requestStageService.getById(id);
		return ResponseEntity.ok(stage);
	}
	
	
}
