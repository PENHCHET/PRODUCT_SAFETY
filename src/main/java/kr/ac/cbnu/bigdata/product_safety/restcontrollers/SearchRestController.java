package kr.ac.cbnu.bigdata.product_safety.restcontrollers;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.ac.cbnu.bigdata.product_safety.utils.Pagination;
import kr.ac.cbnu.bigdata.product_safety.utils.ResponseList;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/api/search")
public class SearchRestController {

    @Autowired
    private TransportClient client;

    @ApiOperation("Search a Product in the Product_Safty Index and Coupang_Products Type")
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public GetResponse test(@PathVariable String productId) {
        GetResponse response = client.prepareGet("korean_docs", "coupang_products", productId).get();
        return response;
    }

    @ApiOperation("Search All Products in the Product_Safty Index and Coupang_Products Type")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public SearchResponse allHits() {
        SearchResponse allHits = client.prepareSearch()
                .setIndices("korean_docs")
                .setTypes("coupang_products")
                .setFrom(0)
                .setSize(15)
                .setQuery(
                        QueryBuilders.matchAllQuery()
                )
                .execute().actionGet();
        return allHits;
    }

    @ApiOperation("Search Products by text in the Product_Safty Index and Coupang_Products Type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue = "1",
                    value = "Result page you want to retrieve (1..N)"),
            @ApiImplicitParam(name = "limit", dataType = "integer", paramType = "query", defaultValue = "15",
                    value = "Number of records per page."),
    })
    @RequestMapping(value = "/products-query", method = RequestMethod.GET)
    public ResponseList<SearchHit> getAllProductsByTextQuery(@RequestParam("query") String query, @ApiIgnore Pagination pagination) {

        ResponseList<SearchHit> response = new ResponseList<>();

        SearchResponse totalCount = client.prepareSearch()
                .setIndices("korean_docs")
                .setTypes("coupang_products")
                .setQuery(
                        QueryBuilders.multiMatchQuery(query, "title","sellingInfo")
                )
                .execute().actionGet();

        pagination.setTotalCount(totalCount.getHits().totalHits);

        SearchResponse allHits = client.prepareSearch()
//                .setIndices("product_safty")
                .setIndices("korean_docs")
                .setTypes("coupang_products")
                .setFrom(pagination.offset())
                .setSize(30)
                .setQuery(
                        QueryBuilders.multiMatchQuery(query, "title","sellingInfo")
                )
                .execute().actionGet();
        response.setCode("0000");
        response.setData(Arrays.asList(allHits.getHits().getHits()));
        response.setPagination(pagination);
        return response;

//curl -XPUT 'localhost:9200/product_safty01?pretty' -H 'Content-Type: application/json' -d'
//{
//    "mappings": {
//        "coupang_products": {
//            "properties": {
//                "title": {
//                    "type":     "text",
//                    "analyzer": "openkoreantext-analyzer"
//                }
//            }
//        }
//    }
//}'

    }
}