package guru.springframework.msscbrewery.web.mappers;

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerMapperTest {

    @Autowired
    BeerMapper beerMapper;

    @Test
    void beerToBeerDto() {
        Beer beer = Beer.builder().id(UUID.randomUUID()).beerName("Golden Pale Ale").beerStyle(BeerStyleEnum.PALE_ALE)
                .createdDate(new Timestamp(LocalDateTime.now().minusDays(1).toEpochSecond(ZoneOffset.UTC)))
                .lastUpdatedDate(new Timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))).build();

        BeerDto beerDto = beerMapper.beerToBeerDto(beer);

        System.out.println(beer);
        System.out.println(beerDto);
    }

    @Test
    void beerDtoToBeer() {
        BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("Dogfish Head 60 Min IPA")
                .beerStyle(BeerStyleEnum.IPA).upc(333L).createdDate(OffsetDateTime.of(LocalDateTime.now().minusDays(1), ZoneOffset.UTC))
                .lastUpdatedDate(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC)).build();

        Beer beer = beerMapper.beerDtoToBeer(beerDto);

        System.out.println(beer);

    }
}