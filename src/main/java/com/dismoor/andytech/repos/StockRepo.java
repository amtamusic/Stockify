package com.dismoor.andytech.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dismoor.andytech.models.Stock;

@Repository
public interface StockRepo extends MongoRepository<Stock, String> {

}
