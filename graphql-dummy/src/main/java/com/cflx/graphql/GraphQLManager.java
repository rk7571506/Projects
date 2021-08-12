package com.cflx.graphql;

import java.util.Map;

import graphql.ExecutionInput;
import graphql.ExecutionResult;

public class GraphQLManager {

	private final GraphQLProvider dataProvider;
	private graphql.GraphQL graphQL;

	public GraphQLManager() {
		dataProvider = GraphQLProvider.getInstance();
		// Build schema
		this.checkAndBuildSchema();
		System.out.println("GraphQL Manage init....");
	}

	public ExecutionResult getData(String query, Map<String, Object> parameters) {
		System.out.println("Executing GraphQLManager.getData.");
		System.out.println("query:{} :: " + query + "\n parameters: {} :: " + parameters);

		// build Input object
		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).variables(parameters).build();
		ExecutionResult result = graphQL.execute(executionInput);
		System.out.println("Exiting GraphQLManager.getData. graphql result isDataPresent: {} :: "
				+ result.isDataPresent() + "\n errors: {}" + result.getErrors());
		return result;
	}

	private void checkAndBuildSchema() {
		if (graphQL == null) {
			dataProvider.buildSchema();
			graphQL = dataProvider.getGraphqlProvider();
		}
	}

}
