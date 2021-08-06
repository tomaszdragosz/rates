package com.hsbc.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@Component
class TestApiClient {

    private MockMvc mockMvc

    @Autowired
    TestApiClient(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    MockHttpServletResponse getForResponse(Map map = [:]) {
        def requestBuilder = MockMvcRequestBuilders.get(map.url)
                .contentType(MediaType.APPLICATION_JSON)
        perform(requestBuilder).andReturn().response
    }

    private def perform(MockHttpServletRequestBuilder request) {
        mockMvc.perform(request)
    }
}
