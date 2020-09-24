package test.com.bestcommerce.product;

import com.bestcommerce.product.ProductApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@AutoConfigureMockMvc
public abstract class AbstractRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public MockMvc getMockMvc() {
        return mockMvc;
    }
}