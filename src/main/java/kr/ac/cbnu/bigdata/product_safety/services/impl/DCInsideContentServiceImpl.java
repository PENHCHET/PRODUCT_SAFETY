package kr.ac.cbnu.bigdata.product_safety.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.cbnu.bigdata.product_safety.models.DCInsideContent;
import kr.ac.cbnu.bigdata.product_safety.repositories.DCInsideContentRepository;
import kr.ac.cbnu.bigdata.product_safety.services.DCInsideContentService;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HP1 on 2018-03-20.
 */
@Service
public class DCInsideContentServiceImpl implements DCInsideContentService {

    @Autowired
    private DCInsideContentRepository dcInsideContentRepository;

    @Autowired
    private TransportClient client;

    @Override
    public List<DCInsideContent> getAllDcInsideContents() {
        try{
            return dcInsideContentRepository.getAllDCInsideContents();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addAllDcInsideContentsIntoElasticsearch() {
        try{
//            Map<String, Object> json = new HashMap<>();
//            json.put("dc_cont_index",112);
//            json.put("board_num",121);
//            json.put("board_title","trying out Elasticsearch");
//
//            IndexResponse response = client.prepareIndex("product_safety", "dc_inside")
//                    .setSource(json)
//                    .execute()
//                    .actionGet();
            BulkRequestBuilder bulkRequest = client.prepareBulk();

            List<DCInsideContent> dcInsideContents = dcInsideContentRepository.getAllDCInsideContents();
            System.out.println(dcInsideContents);
            // instance a json mapper
//            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//
//            // generate json
//            byte[] json = mapper.writeValueAsBytes(dcInsideContents);
//
//            bulkRequest.add(client.prepareIndex("product_safety", "dc_inside")
//                    .setSource(json));
            for(DCInsideContent dcInsideContent: dcInsideContents){
                // instance a json mapper
//                ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//
//                // generate json
//                byte[] json = mapper.writeValueAsBytes(dcInsideContent);


                Map<String, Object> json = new HashMap<>();
                json.put("dc_cont_index", dcInsideContent.getDcContIndex());
                json.put("board_num", dcInsideContent.getBoardNum());
                json.put("board_title", dcInsideContent.getBoardTitle());
                json.put("writer_id", dcInsideContent.getWriterId());
                json.put("content", dcInsideContent.getContent());
                json.put("content_date", dcInsideContent.getContentDate());
                json.put("insert_date", dcInsideContent.getInsertDate());
                System.out.println(json);
                bulkRequest.add(client.prepareIndex("product_safety", "dc_inside")
                        .setSource(json));
            }

            // either use client#prepare, or use Requests# to directly build index/delete requests
//            bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
//                    .setSource(XContentFactory.jsonBuilder()
//                            .startObject()
//                            .field("user", "kimchy")
//                            .field("postDate", new Date())
//                            .field("message", "trying out Elasticsearch")
//                            .endObject()
//                    )
//            );

//            bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
//                    .setSource(XContentFactory.jsonBuilder()
//                            .startObject()
//                            .field("user", "kimchy")
//                            .field("postDate", new Date())
//                            .field("message", "another post")
//                            .endObject()
//                    )
//            );

            BulkResponse bulkResponse = bulkRequest.get();
            if (bulkResponse.hasFailures()) {
                // process failures by iterating through each bulk response item
                System.out.println("BulkResponse Failure...");
                return false;
            }
            System.out.println("BulkResponse Successfully...");
            return true;
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
