package com.cflx.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cflx.graphql.GraphQLInput;
import com.cflx.graphql.GraphQLManager;
import com.cflx.response.ResponseBuilder;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/graphql")
public class EmployeeController {

	private GraphQLManager graphQLManager;

	public EmployeeController() {
		graphQLManager = new GraphQLManager();
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> getCallGraphQLAllAPIs(@RequestBody GraphQLInput input) {
		System.out.println("Executing EmployeeController.getCallGraphQLAllAPIs() method");

		try {
			ExecutionResult result = graphQLManager.getData(input.getQuery(), input.getVariables());
			return ResponseBuilder.buildResponse(result);
		} catch (Exception e) {
			return ResponseBuilder.buildErrorResponse(e);
		} finally {
			System.out.println("Exit EmployeeController.getCallGraphQLAllAPIs() method");
		}
	}

}
