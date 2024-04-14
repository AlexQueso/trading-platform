package alex.quesada.trading.controller;

import alex.quesada.trading.controller.dto.OrderRequest;
import alex.quesada.trading.controller.dto.OrderResponse;
import alex.quesada.trading.exception.SecurityNotFoundException;
import alex.quesada.trading.exception.UserNotFoundException;
import alex.quesada.trading.service.OrderService;
import alex.quesada.trading.utils.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    private static String ID = "1";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private OrderService orderService;

    @Test
    public void givenOrderId_whenGetOrderById_thenReturnCorrectOrderResponse() throws Exception {
        OrderResponse orderResponse = TestData.getOrderResponse();
        orderResponse.setId(ID);
        Mockito.when(orderService.getOrderById(any())).thenReturn(Optional.of(orderResponse));

        mvc.perform(get("/api/v1/orders/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    public void givenOrderRequest_whenSaveNewOrder_thenReturnCorrectOrderResponse() throws Exception {
        OrderResponse orderResponse = TestData.getOrderResponse();
        OrderRequest orderRequest = TestData.getOrderRequest();
        orderResponse.setId(ID);
        Mockito.when(orderService.saveNewOrder(orderRequest)).thenReturn(orderResponse);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/")
                        .content(asJsonString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    public void givenOrderRequest_whenSaveNewOrderAndUserNotFound_thenReturnNotFound404() throws Exception {
        OrderResponse orderResponse = TestData.getOrderResponse();
        OrderRequest orderRequest = TestData.getOrderRequest();
        orderResponse.setId(ID);
        Mockito.when(orderService.saveNewOrder(orderRequest)).thenThrow(new UserNotFoundException(""));

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/")
                        .content(asJsonString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenOrderRequest_whenSaveNewOrderAndSecurityNotFound_thenReturnNotFound404() throws Exception {
        OrderResponse orderResponse = TestData.getOrderResponse();
        OrderRequest orderRequest = TestData.getOrderRequest();
        orderResponse.setId(ID);
        Mockito.when(orderService.saveNewOrder(orderRequest)).thenThrow(new SecurityNotFoundException(""));

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/")
                        .content(asJsonString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}