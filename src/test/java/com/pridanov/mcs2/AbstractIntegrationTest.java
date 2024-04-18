package com.pridanov.mcs2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pridanov.mcs2.config.PostgresTestContainersInitializer;
import com.pridanov.mcs2.repository.OrderRepository;
import com.pridanov.mcs2.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@Transactional
@ContextConfiguration(initializers = {PostgresTestContainersInitializer.class})
@SpringBootTest(classes = McsSoloApplication.class)

public abstract class AbstractIntegrationTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter("UTF-8", true);
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @Autowired
    protected MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderRepository orderRepository;

    @AfterEach
    void cleanAll() {
        orderRepository.deleteAll();
    }

    @PostConstruct
    public void prepare() {
        objectMapper = jacksonMessageConverter.getObjectMapper();

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }
}
