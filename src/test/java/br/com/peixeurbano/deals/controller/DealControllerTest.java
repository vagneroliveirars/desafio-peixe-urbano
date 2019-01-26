package br.com.peixeurbano.deals.controller;

import br.com.peixeurbano.deals.dto.BuyOptionRequestDTO;
import br.com.peixeurbano.deals.dto.BuyOptionResponseDTO;
import br.com.peixeurbano.deals.dto.DealRequestDTO;
import br.com.peixeurbano.deals.dto.DealResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DealControllerTest {

    private static final String DEALS_URI = "/deals";

    @Autowired
    private ApplicationContext context;

    private WebTestClient webTestClient;

    private DealRequestDTO dealRequestDTO;

    @BeforeEach
    public void beforeEach() {
        this.webTestClient = WebTestClient.bindToApplicationContext(this.context).build();

        this.dealRequestDTO = random(DealRequestDTO.class);

        this.dealRequestDTO.getBuyOptions().forEach(buyOptionDTO -> {
            buyOptionDTO.setNormalPrice(new BigDecimal("149.90"));
            buyOptionDTO.setSalePrice(new BigDecimal("99.90"));
            buyOptionDTO.setPercentageDiscount(new BigDecimal("5"));
        });
    }

    @Test
    @DisplayName("Add deal test")
    public void addDeal() {
        URI location = webTestClient
                .post()
                .uri(DEALS_URI)
                .syncBody(dealRequestDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader()
                .exists("Location")
                .returnResult(String.class)
                .getResponseHeaders()
                .getLocation();

        assertNotNull(location);
        assertNotNull(location.getPath());

        DealResponseDTO dealResponseDTO = webTestClient
                .get()
                .uri(location)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(DealResponseDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(dealResponseDTO);
        assertNotNull(dealResponseDTO.getId());
        assertEquals(dealRequestDTO.getTitle(), dealResponseDTO.getTitle());
        assertEquals(dealRequestDTO.getText(), dealResponseDTO.getText());
        assertEquals(dealRequestDTO.getCreateDate(), dealResponseDTO.getCreateDate().withZoneSameInstant(dealRequestDTO.getCreateDate().getZone()));
        assertEquals(dealRequestDTO.getPublishDate(), dealResponseDTO.getPublishDate().withZoneSameInstant(dealRequestDTO.getPublishDate().getZone()));
        assertEquals(dealRequestDTO.getEndDate(), dealResponseDTO.getEndDate().withZoneSameInstant(dealRequestDTO.getEndDate().getZone()));
        assertNotNull(dealResponseDTO.getUrl());
        assertEquals(dealRequestDTO.getTotalSold(), dealResponseDTO.getTotalSold());
        assertEquals(dealRequestDTO.getType(), dealResponseDTO.getType());
        assertNotNull(dealResponseDTO.getBuyOptions());
        assertEquals(dealRequestDTO.getBuyOptions().size(), dealResponseDTO.getBuyOptions().size());

        dealRequestDTO.getBuyOptions().forEach(buyOptionRequestDTO -> {
            assertTrue(dealResponseDTO.getBuyOptions().stream().anyMatch(buyOptionResponseDTO -> equalsBuyOptions(buyOptionRequestDTO, buyOptionResponseDTO)));
        });

        List<DealResponseDTO> deals = webTestClient
                .get()
                .uri(DEALS_URI)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(DealResponseDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(deals);
        assertEquals(1, deals.size());

        Optional<DealResponseDTO> anyDeal = deals.stream().filter(deal -> deal.getId().equals(dealResponseDTO.getId())).findAny();
        assertTrue(anyDeal.isPresent());
        assertEquals(dealRequestDTO.getTitle(), anyDeal.get().getTitle());
        assertEquals(dealRequestDTO.getText(), anyDeal.get().getText());
        assertEquals(dealRequestDTO.getCreateDate(), anyDeal.get().getCreateDate().withZoneSameInstant(dealRequestDTO.getCreateDate().getZone()));
        assertEquals(dealRequestDTO.getPublishDate(), anyDeal.get().getPublishDate().withZoneSameInstant(dealRequestDTO.getPublishDate().getZone()));
        assertEquals(dealRequestDTO.getEndDate(), anyDeal.get().getEndDate().withZoneSameInstant(dealRequestDTO.getEndDate().getZone()));
        assertNotNull(anyDeal.get().getUrl());
        assertEquals(dealRequestDTO.getTotalSold(), anyDeal.get().getTotalSold());
        assertEquals(dealRequestDTO.getType(), anyDeal.get().getType());
        assertNotNull(anyDeal.get().getBuyOptions());
        assertEquals(dealRequestDTO.getBuyOptions().size(), anyDeal.get().getBuyOptions().size());

        webTestClient
                .delete()
                .uri(location)
                .exchange()
                .expectStatus()
                .isNoContent();

        webTestClient
                .get()
                .uri(location)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    private boolean equalsBuyOptions(BuyOptionRequestDTO buyOptionRequest,
                                     BuyOptionResponseDTO buyOptionResponse) {
        return Objects.equals(buyOptionRequest.getTitle(), buyOptionResponse.getTitle()) &&
                buyOptionRequest.getNormalPrice().compareTo(buyOptionResponse.getNormalPrice()) == 0 &&
                buyOptionRequest.getSalePrice().compareTo(buyOptionResponse.getSalePrice()) == 0 &&
                buyOptionRequest.getPercentageDiscount().compareTo(buyOptionResponse.getPercentageDiscount()) == 0 &&
                Objects.equals(buyOptionRequest.getQuantityCupom(), buyOptionResponse.getQuantityCupom()) &&
                Objects.equals(buyOptionRequest.getStartDate(), buyOptionResponse.getStartDate().withZoneSameInstant(buyOptionRequest.getStartDate().getZone())) &&
                Objects.equals(buyOptionRequest.getEndDate(), buyOptionResponse.getEndDate().withZoneSameInstant(buyOptionRequest.getEndDate().getZone()));
    }


}
