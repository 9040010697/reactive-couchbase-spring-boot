package com.cb.model;


import com.couchbase.client.java.repository.annotation.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MlcCard {

    @Field
    private String mlcCardNo;
    @Field
    private boolean active;
}
