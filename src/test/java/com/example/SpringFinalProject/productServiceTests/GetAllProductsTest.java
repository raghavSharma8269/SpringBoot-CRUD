package com.example.SpringFinalProject.productServiceTests;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest

public class GetAllProductsTest {

    private MockMvc mockMvc;

    public GetAllProductsTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

   public void TestGetALlProductsEndpoint() throws Exception{
        
   }

}
