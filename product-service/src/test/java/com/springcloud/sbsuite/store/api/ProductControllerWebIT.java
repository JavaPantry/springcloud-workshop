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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// for status().isCreated()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerWebIT {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ProductController productController;

	@MockBean
	ProductService productService;

	@Autowired
	ObjectMapper objectMapper;
	@Test
	void testCreateNewBeer() throws Exception {
		//ProductDto product = new ProductDto().builder().name("test product").description("test product description").build();
		ProductDto product = new ProductDto().builder().build();


		//given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().get(1));

		mockMvc.perform(post("/product/")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(product)))
			/*	.andExpect(status().isCreated())
		.andExpect(header().exists("Location"))*/;
	}

}
