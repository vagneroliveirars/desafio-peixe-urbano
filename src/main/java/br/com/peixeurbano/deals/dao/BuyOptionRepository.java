package br.com.peixeurbano.deals.dao;

import br.com.peixeurbano.deals.entity.BuyOption;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BuyOptionRepository extends ReactiveMongoRepository<BuyOption, String> {

}
