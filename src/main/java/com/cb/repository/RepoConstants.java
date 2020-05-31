package com.cb.repository;

public class RepoConstants {
  public static final String MLC_CARD_SEARCH_QUERY = "#{#n1ql.selectEntity} "
          + "WHERE ANY mlcCard IN mlcCards SATISFIES mlcCard.mlcCardNo = $1 END";

  public static final String PHONE_NUMBER_SEARCH_QUERY
          = "#{#n1ql.selectEntity} WHERE phoneNumber = $1";


  public static final String MLC_CARD_PHONE_NUMBER_SEARCH_QUERY =
          "#{#n1ql.selectEntity} WHERE phoneNumber "
                  + "= $2 AND ANY mlcCard IN mlcCards SATISFIES mlcCard.mlcCardNo = $1 END";
}
