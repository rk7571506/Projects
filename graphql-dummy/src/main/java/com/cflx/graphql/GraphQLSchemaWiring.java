package com.cflx.graphql;

import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;

public class GraphQLSchemaWiring {

	public static RuntimeWiring buildRuntimeWiring() {

		GraphQLDataFetcherImpl graphQLDataFetcherImpl = new GraphQLDataFetcherImpl();
		return RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query")
						.dataFetcher("lookupEmployeeByID", graphQLDataFetcherImpl.getLookupEmployeeByID())
						.dataFetcher("lookupEmployee", graphQLDataFetcherImpl.getLookupEmployee()))
				.type(TypeRuntimeWiring.newTypeWiring("Mutation")
						.dataFetcher("updateEmployee", graphQLDataFetcherImpl.updateEmployee())
						.dataFetcher("createEmployee", graphQLDataFetcherImpl.createEmployee())
						.dataFetcher("deleteEmployee", graphQLDataFetcherImpl.deleteEmployee()))
				.build();
	}

}
