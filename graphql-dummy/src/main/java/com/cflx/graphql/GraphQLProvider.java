package com.cflx.graphql;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class GraphQLProvider {

	private static final String GRAPHQL_FILE_NAME = "emp.graphql";
	private static GraphQLProvider graphqlProvider;
	private GraphQLSchema graphQLSchema;

	public static GraphQLProvider getInstance() {
		if (graphqlProvider == null) {
			graphqlProvider = new GraphQLProvider();
		}
		return graphqlProvider;
	}

	public graphql.GraphQL getGraphqlProvider() {
		return graphql.GraphQL.newGraphQL(graphQLSchema).build();
	}

	public void buildSchema() {
		InputStream stream = null;
		Reader reader = null;
		try {
			ClassLoader classLoader = GraphQLProvider.class.getClassLoader();
			stream = classLoader.getResourceAsStream(GRAPHQL_FILE_NAME);

			if (stream == null) {
				throw new RuntimeException("Cannot read Graphl schema!. File: " + GRAPHQL_FILE_NAME);
			}

			reader = new InputStreamReader(stream);
			SchemaGenerator schemaGenerator = new SchemaGenerator();
			SchemaParser schemaParser = new SchemaParser();
			TypeDefinitionRegistry registry = schemaParser.parse(reader);
			RuntimeWiring wiring = GraphQLSchemaWiring.buildRuntimeWiring();
			graphQLSchema = schemaGenerator.makeExecutableSchema(registry, wiring);

		} catch (Exception e) {
			throw new RuntimeException("Cannot build Graphql schema. Error: ", e);

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				System.out.println("============Inside catch=========");
			}
		}
	}

}
