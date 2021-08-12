package com.cflx.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import graphql.ErrorType;
import graphql.ExecutionResult;
import graphql.GraphQLError;

public class ResponseBuilder extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int statusCode;

	public ResponseBuilder(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public static ResponseEntity<Object> buildResponse(ExecutionResult result) {
		System.out.println("Building response for graphql data services...");

		if (result.getData() == null) {
			if (result.getErrors() == null || result.getErrors().isEmpty()) {
				return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
			} else {
				for (GraphQLError graphQLError : result.getErrors()) {
					if (graphQLError.getErrorType().equals(ErrorType.DataFetchingException)) {
						if (graphQLError.getMessage().contains("Invalid ")
								|| graphQLError.getMessage().contains("Failed to")
								|| graphQLError.getMessage().contains("Data Extraction failed")
								|| graphQLError.getMessage().contains("Schema validation failed")
								|| graphQLError.getMessage().contains("already exists")) {
							return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
						} else {
							return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else if (graphQLError.getErrorType().equals(ErrorType.ValidationError)
							|| graphQLError.getErrorType().equals(ErrorType.InvalidSyntax)) {
						return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
					} else if (graphQLError.getErrorType().equals(ErrorType.OperationNotSupported)
							|| graphQLError.getErrorType().equals(ErrorType.ExecutionAborted)) {
						return new ResponseEntity<>(result, HttpStatus.METHOD_NOT_ALLOWED);
					} else {
						return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			}
		} else {
			if (result.getErrors() == null || result.getErrors().isEmpty()) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(result, HttpStatus.PARTIAL_CONTENT);
			}
		}
		return null;
	}

	public static ResponseEntity<Object> buildErrorResponse(Exception e) {
		System.out.println("Building error response for graphql data services ... " + e);

		HttpStatus statusCode;
		if (e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
			statusCode = HttpStatus.BAD_REQUEST;
		} else if (e instanceof ApplicationException) {
			statusCode = HttpStatus.FORBIDDEN;
		} else {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		System.out.println("StatusCode: {} " + statusCode);
		Map<String, Object> error = new HashMap<>();
		error.put("message", e.getMessage());
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(error);
		Map<String, Object> result = new HashMap<>();
		result.put("errors", list);
		return new ResponseEntity<>(result, statusCode);
	}
}
