package com.springcloud.sbsuite.store.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.sbsuite.store.dto.ProductDto;
import com.springcloud.sbsuite.store.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// for post(..)
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.*;
// for status().isCreated()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerWebIT {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@Autowired
	ObjectMapper objectMapper;
	@Test
	void testCreateNewProduct() throws Exception {
		ProductDto product = new ProductDto().builder().name("test product").description("test product description").build();

		given(productService.saveProduct(any(ProductDto.class))).willReturn(Optional.ofNullable(product));

		mockMvc.perform(post("/product")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(product)))
					.andExpect(status().isCreated());
		/*.andExpect(header().exists("Location"))*/;
	}

	@Test
	void testCreateNewInvalidProduct() throws Exception {
		ProductDto product = new ProductDto().builder().build();

		given(productService.saveProduct(any(ProductDto.class))).willReturn(Optional.ofNullable(product));

		mockMvc.perform(post("/product")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isBadRequest());
		/*.andExpect(header().exists("Location"))*/;
	}


}
