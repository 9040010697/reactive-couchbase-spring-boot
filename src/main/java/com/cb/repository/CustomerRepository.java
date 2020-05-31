package com.cb.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.cb.model.Customer;

import reactor.core.publisher.Mono;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "customer", viewName = "all")
public interface CustomerRepository extends ReactiveCouchbaseRepository<Customer, String> {

    @Query(RepoConstants.PHONE_NUMBER_SEARCH_QUERY)
    Mono<Customer> findByPhoneNumber(String phoneNumber);

    @Query(RepoConstants.MLC_CARD_SEARCH_QUERY)
    Mono<Customer> findByMlcCardNo(String mlcCardNo);

    @Query(RepoConstants.MLC_CARD_PHONE_NUMBER_SEARCH_QUERY)
    Mono<Customer> findByMlcCardNoAndPhoneNumber(String mlcCardNo, String phoneNumber);
    

}
