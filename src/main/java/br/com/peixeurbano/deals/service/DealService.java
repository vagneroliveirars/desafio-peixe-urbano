package br.com.peixeurbano.deals.service;

import br.com.peixeurbano.deals.dao.DealRepository;
import br.com.peixeurbano.deals.entity.BuyOption;
import br.com.peixeurbano.deals.entity.Deal;
import br.com.peixeurbano.deals.exception.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DealService {

    private final DealRepository dealRepository;

    private final BuyOptionService buyOptionService;

    public DealService(DealRepository dealRepository,
                       BuyOptionService buyOptionService) {
        this.dealRepository = dealRepository;
        this.buyOptionService = buyOptionService;
    }

    public Mono<Deal> insert(Deal deal) {
        return buyOptionService
                .saveAll(deal.getBuyOptions())
                .collectList()
                .flatMap(buyOptions -> {
                    deal.setUrl(buildUniqueUrl());
                    return dealRepository.save(deal);
                });
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
                .flatMap(deal -> buyOptionService.deleteAll(deal.getBuyOptions()))
                .then(dealRepository.deleteById(id));
    }

    public Mono<Deal> checkout(String dealId, String buyOptionId) {
        return findById(dealId)
                .flatMap(deal -> findBuyOptionById(deal, buyOptionId)
                        .switchIfEmpty(Mono.error(new NotFoundException("Buy option not found")))
                        .flatMap(this::sellUnity)
                        .flatMap(buyOption -> updateTotalSold(deal))
                );
    }

    private String buildUniqueUrl() {
        //TODO build unique url based on deal's title
        return UUID.randomUUID().toString();
    }

    private Mono<BuyOption> findBuyOptionById(Deal deal, String id) {
        return deal
                .getBuyOptions()
                .stream()
                .filter(buyOption -> buyOption.getId().equals(id))
                .findAny()
                .map(Mono::just)
                .orElseGet(Mono::empty);
    }

    private Mono<BuyOption> sellUnity(BuyOption buyOption) {
        buyOption.sellUnity(1l);
        return buyOptionService.save(buyOption);
    }

    private Mono<Deal> updateTotalSold(Deal deal) {
        deal.updateTotalSold(1l);
        return dealRepository.save(deal);
    }

}
