package com.cb;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;
import org.springframework.http.ResponseEntity;

import com.cb.model.Customer;
import com.cb.model.MlcCard;
import com.cb.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication
@EnableReactiveCouchbaseRepositories
@EnableSwagger2WebFlux
public class BootLuncher {


    public static void main(String[] args) {
        SpringApplication.run(BootLuncher.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return e -> repository.saveAll(getCustList()).subscribe();
    }

 
    private List<Customer> getCustList() {
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer cust = new Customer();
            cust.setCId(UUID.randomUUID().toString());
            cust.setPhoneNumber(String.valueOf(new Random().nextInt(2147483647)));
            cust.setRoles(new String[]{"PRO", "DIY"});

            List<MlcCard> cards = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                MlcCard card = new MlcCard();
                card.setMlcCardNo(String.valueOf(new Random().nextInt(2147483647)));
                card.setActive(new Random().nextBoolean());
                cards.add(card);
            }
            cust.setMlcCards(cards);
            list.add(cust);
        }

        return list;
    }
    
    
    @Bean
    public Docket customApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .protocols(new HashSet<>(Arrays.asList("http", "https")))
              .select()
              .paths(PathSelectors.any())
              .apis(RequestHandlerSelectors.basePackage("org.springframework.boot").negate())
              .apis(RequestHandlerSelectors.basePackage("org.springframework.cloud").negate())
              .apis(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")
                      .negate())
              .apis(RequestHandlerSelectors.basePackage("com.cb.controller"))
              .build()
              .useDefaultResponseMessages(false)
              .ignoredParameterTypes(Mono.class, Flux.class, ResponseEntity.class)
              .directModelSubstitute(LocalDate.class, Date.class)
              .directModelSubstitute(OffsetDateTime.class, Date.class)
              .apiInfo(ApiInfo.DEFAULT);
    }
}
