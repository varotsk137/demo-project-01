package com.playground.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.playground.demo.model.entity.Tag;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadInputUtil {

    private ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public String loadJsonFileToString(String jsonFileName) throws IOException {

        jsonFileName = StringUtils.trim(jsonFileName);
        InputStream inputStream = getClass().getResourceAsStream(jsonFileName);
        return readFromInputStream(inputStream);

    }

    public <T> T loadClassFromJson(Class<T> clazz, String jsonFileName) throws IOException {

        jsonFileName = StringUtils.trim(jsonFileName);
        InputStream inputStream = clazz.getResourceAsStream(jsonFileName);

        return objectMapper.readValue(inputStream, clazz);

    }

    public List loadClassListFromJson(TypeReference type, String jsonFileName) throws  IOException{

        String tagString = loadJsonFileToString(jsonFileName);
        List tagList = (List) objectMapper.convertValue(objectMapper.readValue(tagString, List.class), type );

        return tagList;
    }

}
