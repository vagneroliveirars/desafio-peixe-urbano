package br.com.peixeurbano.deals.service;

import br.com.peixeurbano.deals.dao.BuyOptionRepository;
import br.com.peixeurbano.deals.entity.BuyOption;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BuyOptionService {

    private final BuyOptionRepository buyOptionRepository;

    public BuyOptionService(BuyOptionRepository buyOptionRepository) {
        this.buyOptionRepository = buyOptionRepository;
    }

    public Mono<BuyOption> save(BuyOption buyOption) {
        return buyOptionRepository.save(buyOption);
    }

    public Flux<BuyOption> saveAll(List<BuyOption> buyOptions) {
        return buyOptionRepository.saveAll(buyOptions);
    }

    public Mono<Void> deleteAll(List<BuyOption> buyOptions) {
        return buyOptionRepository.deleteAll(buyOptions);
    }

}
