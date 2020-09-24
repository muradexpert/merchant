package test.com.bestcommerce.product;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractRestControllerTest {
    public static String token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfTUVSQ0hBTlQiLCJJRCI6MSwiZXhwIjoxNjAwOTg0NDEyfQ.Hm3TEzNK95KODLihdhdlJ7llcahoDHS3UpGtYr3KGk0LzRrIro-n9QFPq3F_M-VIZeNjIXqlmUfZ86ex8Kva-g";

    @Test
    public void addProduct() throws Exception {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,token);

        getMockMvc().perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders)
                .content("{\"name\": \"phone\", \"price\": 15.99}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateProduct() throws Exception {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,token);

        getMockMvc().perform(post("/update/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders)
                .content("{\"name\": \"phone\", \"price\": 15.99}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllProducts() throws Exception {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,token);

        getMockMvc().perform(get("/getAllByUser").param("page","0").param("size","1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllProductsOrderBy() throws Exception {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,token);

        getMockMvc().perform(get("/getAllOrderBy")
                .param("page","0")
                .param("size","1")
                .param("sortType","price_asc")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
    }
}