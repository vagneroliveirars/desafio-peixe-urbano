package br.com.peixeurbano.deals.controller;

import br.com.peixeurbano.deals.dto.DealRequestDTO;
import br.com.peixeurbano.deals.dto.DealResponseDTO;
import br.com.peixeurbano.deals.entity.Deal;
import br.com.peixeurbano.deals.service.DealService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/deals")
public class DealController {

    private final DealService dealService;

    private final MapperFacade mapper;

    public DealController(DealService dealService,
                          MapperFacade mapper) {
        this.dealService = dealService;
        this.mapper = mapper;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> add(@RequestBody @Valid DealRequestDTO dealRequestDTO,
                                          ServerHttpRequest httpRequest) {
        return dealService
                .insert(mapper.map(dealRequestDTO, Deal.class))
                .map(deal -> {
                    URI location = UriComponentsBuilder
                            .fromHttpRequest(httpRequest)
                            .path("/{id}")
                            .buildAndExpand(deal.getId())
                            .toUri();

                    return ResponseEntity.created(location).build();
                });
    }

    @GetMapping
    public Flux<DealResponseDTO> findAll() {
        return dealService
                .findAll()
                .map(deal -> mapper.map(deal, DealResponseDTO.class));
    }

    @GetMapping("/{id}")
    public Mono<DealResponseDTO> findById(@PathVariable String id) {
        return dealService
                .findById(id)
                .map(deal -> mapper.map(deal, DealResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable String id) {
        return dealService.deleteById(id);
    }

}
