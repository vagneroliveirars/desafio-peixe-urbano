package br.com.peixeurbano.deals.service;

import br.com.peixeurbano.deals.dao.DealRepository;
import br.com.peixeurbano.deals.entity.Deal;
import br.com.peixeurbano.deals.exception.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DealService {

    private final DealRepository dealRepository;

    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public Mono<Deal> insert(Deal deal) {
        deal.setUrl(buildUniqueUrl());
        return dealRepository.insert(deal);
    }

    public Flux<Deal> findAll() {
        return dealRepository.findAll();
    }

    public Mono<Deal> findById(String id) {
        return dealRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Deal not found")));
    }

    public Mono<Void> deleteById(String id) {
        return findById(id)
                .flatMap(deal -> dealRepository.deleteById(deal.getId()));
    }

    public String buildUniqueUrl() {
        //TODO Build a url based on the deal's title
        return UUID.randomUUID().toString();
    }

}
