package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.tests.ProductFactory;
import com.devsuperior.dscommerce.tests.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;

    private String productName;
    private String clientUsername, clientPassword;
    private String adminUsername, adminPassword;
    private String adminToken, clientToken;
    private Long dependableId, existinId, nonExistingId;
    private ProductDTO productDTO;


    @BeforeEach
    void setUp() throws Exception {
        productName = "Macbook";
        clientUsername = "maria@gmail.com";
        clientPassword = "123456";
        adminUsername = "alex@gmail.com";
        adminPassword = "123456";
        nonExistingId = 0L;
        dependableId = 1L;
        existinId = 3L;
        productDTO = ProductFactory.createProductDTO();

        adminToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        clientToken = tokenUtil.obtainAccessToken(mockMvc, clientUsername, clientPassword);
    }

    @Test
    public void findAllShouldReturnPageWhenNameParamIsEmpty() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content[0].id").value(1L));
        result.andExpect(jsonPath("$.content[0].name").value("The Lord of the Rings"));
        result.andExpect(jsonPath("$.content[0].price").value(90.5));
        result.andExpect(jsonPath("$.content[1].id").value(2L));
        result.andExpect(jsonPath("$.content[1].name").value("Smart TV"));
        result.andExpect(jsonPath("$.content[1].price").value(2190.0));
    }

    @Test
    public void findAllShouldReturnPageWhenNamePAramIsNotEmpty() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/products?name={productName}", productName)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content[0].id").value(3L));
        result.andExpect(jsonPath("$.content[0].name").value("Macbook Pro"));
        result.andExpect(jsonPath("$.content[0].price").value(1250.0));
    }

    @Test
    public void insertShouldInsertResourceWhenAdminLoggedAndCorrectData() throws Exception {


        ProductDTO dto = ProductFactory.createProductDTO();
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(dto.getName()));
        result.andExpect(jsonPath("$.description").value(dto.getDescription()));
        result.andExpect(jsonPath("$.price").value(dto.getPrice()));
        result.andExpect(jsonPath("$.imgUrl").value(dto.getImgUrl()));
        result.andExpect(jsonPath("$.categories").isArray());
        result.andExpect(jsonPath("$.categories[0].id").value(dto.getCategories().get(0).getId()));
    }

    @Test
    public void insertShouldReturn422WhendNameInvalidAndAdminLogged() throws Exception {
        Product product = ProductFactory.createProduct();
        product.setName("");
        ProductDTO dto = new ProductDTO(product);
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void insertShouldReturn422WhenDescriptionInvalidAndAdminLogged() throws Exception {

        Product product = ProductFactory.createProduct();
        product.setDescription("");
        ProductDTO dto = new ProductDTO(product);
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void insertShouldReturn422WhenPriceIsNegativeAndAdminLogged() throws Exception {
        Product product = ProductFactory.createProduct();
        product.setPrice(-10.0);
        ProductDTO dto = new ProductDTO(product);
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void insertShouldReturn422WhenPriceIsZeroAndAdminLogged() throws Exception {
        Product product = ProductFactory.createProduct();
        product.setPrice(0.0);
        ProductDTO dto = new ProductDTO(product);
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void insertShouldReturn422WhenNoAssociatedCategoryAndAdminLogged() throws Exception {
        Product product = ProductFactory.createProduct();
        product.getCategories().clear();
        ProductDTO dto = new ProductDTO(product);
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }


    @Test
    public void insertShouldReturn403WhenClientLogged() throws Exception {
        ProductDTO dto = ProductFactory.createProductDTO();
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + clientToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isForbidden());
    }

    @Test
    public void insertShouldReturn401WhenNotLogged() throws Exception {
        ProductDTO dto = ProductFactory.createProductDTO();
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer ")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteShouldReturnNothingWhenIdExistsAndAdminLogged() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/products/{id}", existinId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturn404WhenProductDoesNotExistsAndAdminLogged() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/products/{id}", nonExistingId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteShouldReturn400WhenProductDependentAndAdminLogged() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/products/{id}", dependableId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }


    @Test
    public void deleteShouldReturn403WhenProductExistsAndClientLogged() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/products/{id}", dependableId)
                        .header("Authorization", "Bearer " + clientToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isForbidden());
    }


    @Test
    public void deleteShouldReturn401WhenProductExistsAndClientLogged() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/products/{id}", dependableId)
                        .header("Authorization", "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }
}

