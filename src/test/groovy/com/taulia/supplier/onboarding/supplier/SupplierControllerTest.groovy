package com.taulia.supplier.onboarding.supplier

import com.taulia.supplier.onboarding.supplier.transport.SupplierMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest(controllers = SupplierController.class)
@ContextConfiguration(classes = [SupplierMapper.class])
class SupplierControllerTest {

    @MockBean
    private SupplierService supplierService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() {}

    @Test
    void update() {}

    //todo add more tests for all scenarios this is just a template
    @Test
    void getAllSuppliersPaginated() throws Exception {
        // given
        var pagedRequest = PageRequest.of(0, 10);

        // when
        MvcResult result =
                mockMvc
                        .perform(
                                MockMvcRequestBuilders.post("/api/v0/suppliers")
                                //
                                // .with(user(TEST_USER_ID))
                                        .with(csrf())
                                        .requestAttr("page", pagedRequest)
                                //                    .content(birthday)
                                //                    .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
        // then
        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        //    assertEquals(dow, resultDOW);
    }
}
