package com.playground.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.playground.demo.model.GameDto;
import com.playground.demo.model.entity.Tag;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReadInputUtilTest {

    @InjectMocks
    ReadInputUtil readInputUtil;

//    ReadInputUtil readInputUtil = new ReadInputUtil();

    private ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @SneakyThrows
    @Test
    void loadClassFromJson() {

        GameDto gameDto = readInputUtil.loadClassFromJson(GameDto.class, "/json/gameDto.json");

        assertNotNull(gameDto);
    }

    @SneakyThrows
    @Test
    void loadClassFromJson_testList() {

        String tagString = readInputUtil.loadJsonFileToString("/json/tagList.json");
        List<Tag> tagList = objectMapper.convertValue(objectMapper.readValue(tagString, List.class), new TypeReference<List<Tag>>() {
        });

        assertNotNull(tagList);

    }
    @SneakyThrows
    @Test
    void loadClassFromJson_testList2() {

        List<Tag> tagList = readInputUtil.loadClassListFromJson(new TypeReference<List<Tag>>() {}, "/json/tagList.json");

        assertNotNull(tagList);

    }
}