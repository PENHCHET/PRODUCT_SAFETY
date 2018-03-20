package kr.ac.cbnu.bigdata.product_safety.restcontrollers;

import io.swagger.annotations.ApiOperation;
import kr.ac.cbnu.bigdata.product_safety.services.DCInsideContentService;
import kr.ac.cbnu.bigdata.product_safety.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PENHCHET on 2018-03-20.
 */

@RestController
@RequestMapping("/v1/api/dcinside-contents")
public class DCInsideContentRestController {

    @Autowired
    private DCInsideContentService dcInsideContentService;

    @ApiOperation("Get all data from dcinside-content table insert into the Elasticsearch")
    @RequestMapping(value="/addAllDataFromDCInsideContentsIntoElasticsearch", method = RequestMethod.POST)
    public Response addAllDataFromDCInsideContentsIntoElasticsearch() {
       Response response = new Response();
       if(dcInsideContentService.addAllDcInsideContentsIntoElasticsearch()) {
           response.setCode("0000");
       } else {
           response.setCode("9999");
       }
       return response;
    }
}
