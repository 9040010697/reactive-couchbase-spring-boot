# spring-boot-couchbase_N1QL_Query

For Swagger u need to add libreary

## Search N1QL Queries for Couchbase
-------------------------------------
### Data Model
~~~
{
  "phoneNumber": "1256280946",
  "roles": [
    "PRO",
    "DIY"
  ],
  "mlcCards": [
    {
      "mlcCardNo": "645235276",
      "active": true
    },
    {
      "mlcCardNo": "1115299093",
      "active": false
    }
  ],
  "cid": "a8b131e7-cf35-459c-b295-01eb62a55017"
}
~~~

### Find By MLC CardNo:
~~~
 > SELECT META(`test`).id AS _ID, META(`test`).cas AS _CAS, `test`.* FROM `test` WHERE ANY mlcCard IN mlcCards SATISFIES mlcCard.mlcCardNo = $1 END

 > @Query("#{#n1ql.selectEntity} WHERE ANY mlcCard IN mlcCards SATISFIES mlcCard.mlcCardNo = $1 END")
~~~

### Find By PhoneNumber:
~~~
 > SELECT META(`test`).id AS _ID, META(`test`).cas AS _CAS, `test`.* FROM `test` WHERE `_class` = \"com.cb.model.Customer\" and phoneNumber = $1
 
 >@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND phoneNumber = $1")
 
 >#{#n1ql.selectEntity} WHERE phoneNumber = $1
~~~

### Find By PhoneNumberAndCardNumber:
~~~
  > SELECT META(`test`).id AS _ID, META(`test`).cas AS _CAS, `test`.* FROM `test` WHERE `_class` = \"com.cb.model.Customer\" AND phoneNumber = $2 AND ANY mlcCard IN mlcCards SATISFIES mlcCard.mlcCardNo = $1 END
 
 > @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND phoneNumber=$2 AND ANY mlcCard IN mlcCards SATISFIES mlcCard.mlcCardNo = $1 END")

> #{#n1ql.selectEntity} WHERE phoneNumber=$2 AND ANY mlcCard IN mlcCards SATISFIES mlcCard.mlcCardNo = $1END
~~~
### Create Index withprimary key
CREATE PRIMARY INDEX `#primary` ON `test`
