package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.services.BeerServiceImpl;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
public class BeerControllerTest  {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    public void testGetCustomerById() {
    }

    public BeerDto ValidInputBeer() {
        return BeerDto.builder().id(null).beerName("Golden Brew").beerStyle(BeerStyleEnum.LAGER).upc(1232313L).build();
    }

    public BeerDto InvalidInputBeer() {
        return BeerDto.builder().id(UUID.randomUUID()).beerName(null).beerStyle(BeerStyleEnum.LAGER).upc(1232313L).build();
    }


    public BeerDto ValidBeer() {
        return BeerDto.builder().id(UUID.randomUUID()).beerName("Golden Brew").beerStyle(BeerStyleEnum.PALE_ALE).upc(1232313L).build();
    }


    @Test
    public void testHandleValidPost() throws Exception {


        BeerDto beerDto = this.ValidInputBeer();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(ValidBeer());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());


    }

    @Test
    public void testHandleInvalidPost() throws Exception {


        BeerDto beerDto = this.InvalidInputBeer();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(ValidBeer());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isBadRequest());


    }

    public void testHandleUpdate() {
    }

    public void testDeleteBeer() {
    }
}