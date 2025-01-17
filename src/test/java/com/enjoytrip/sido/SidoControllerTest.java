package com.enjoytrip.sido;

import com.enjoytrip.model.Gugun;
import com.enjoytrip.model.Sido;
import com.enjoytrip.sido.controller.SidoController;
import com.enjoytrip.sido.dto.GugunDto;
import com.enjoytrip.sido.dto.SidoDto;
import com.enjoytrip.sido.service.SidoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.inject.Inject;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
public class SidoControllerTest {


    private Logger logger= LoggerFactory.getLogger(SidoController.class);

    MockMvc mockMvc;


    @InjectMocks
    SidoController sidoController;
    @Mock
    SidoService sidoService;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(sidoController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))  // 한글 깨짐 처리
                .build();
    }



    @Test
    @DisplayName("대한민국 도 조회")
    public void getSido() throws Exception {
        given(sidoService.getSidoList())
                .willReturn(Arrays.asList(new SidoDto(Sido.builder().sidoName("서울").sidoCode(1).build())));
        mockMvc.perform(get("/sido"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("[{\"sidoCode\":1,\"sidoName\":\"서울\"}]"))
                .andDo(print());
    }
    @Test
    @DisplayName("대한민국 군구 조회")
    public void getGugunList() throws Exception {
        given(sidoService.getGugunList(1))
                .willReturn(Arrays.asList(new GugunDto(Gugun.builder().gugunName("특별시").gugunCode(1).build())));

        mockMvc.perform(get("/gugun/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("[{\"gugunCode\":1,\"gugunName\":\"특별시\"}]"))
                .andDo(print());
    }
}
