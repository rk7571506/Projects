package com.sample;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Main {

	private static ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	private static Map<String, Object> readJsonFile() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("C:\\Users\\Confluxsys\\practice\\json-sample\\src\\main\\resources\\input.json");
		Map<String, Object> json = objectMapper.readValue(file, Map.class);
		System.out.println(json);
		return json;
	}

	// {1={type=circle, radius=2}, 2={type=rectangle, l=2, b=4}, 3={type=square,
	// side=2}}
	@SuppressWarnings({ "unused", "unchecked" })
	private static JsonNode buildOutput(Map<String, Object> jsonInput) {
		ObjectNode node = objectMapper.createObjectNode();

		for (Entry<String, Object> o : jsonInput.entrySet()) {
			String id = o.getKey();
			if (o.getValue() instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) o.getValue();
				String type = map.get("type").toString();
				if ("circle".equals(type)) {
					int radius = Integer.valueOf(map.get("radius").toString());
					float circleArea = calCircleArea(radius);
					node.put(id, String.valueOf(circleArea));
				} else if ("rectangle".equals(type)) {
					int l = Integer.valueOf(map.get("l").toString());
					int b = Integer.valueOf(map.get("b").toString());
					int retArea = calRectArea(l, b);
					node.put(id, String.valueOf(retArea));
				} else if ("square".equals(type)) {
					int side = Integer.valueOf(map.get("side").toString());
					int squareArea = calSquareArea(side);
					node.put(id, String.valueOf(squareArea));
				} else {
					System.out.println("Invalid Type");
				}
			}
		}
		return node;
	}

	private static int calSquareArea(int side) {
		return side * side;
	}

	private static int calRectArea(int l, int b) {
		return l * b;
	}

	private static float calCircleArea(int radius) {
		float area = (float) (3.14 * radius * radius);
		return area;
	}

	@SuppressWarnings("unused")
	private static void writeJson(JsonNode node) throws JsonGenerationException, JsonMappingException, IOException {
		objectMapper.writeValue(
				new File("C:\\Users\\Confluxsys\\practice\\json-sample\\src\\main\\resources\\output.json"), node);
		System.out.println("Write SuccessFully.");
	}

	public static void main(String[] args) throws JsonMappingException, IOException {
		Map<String, Object> jsonInput = readJsonFile();
		JsonNode node = buildOutput(jsonInput);
		System.out.println(node);
		writeJson(node);

	}

}
