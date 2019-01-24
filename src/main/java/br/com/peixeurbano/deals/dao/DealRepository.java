package br.com.peixeurbano.deals.dao;

import br.com.peixeurbano.deals.entity.Deal;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DealRepository extends ReactiveMongoRepository<Deal, String> {

}
