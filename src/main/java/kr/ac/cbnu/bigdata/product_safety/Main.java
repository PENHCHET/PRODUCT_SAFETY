package kr.ac.cbnu.bigdata.product_safety;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Main {

    public static void main(String args[]){
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "Elasticsearch")
                    .put("client.transport.sniff", true)
                    .put("transport.tcp.compress", true)
                    .build();

            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("210.115.182.241"), 9300))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("210.115.187.98"), 9300));
            System.out.println("Connection Sucesssfully");




//            SearchResponse allHits = client.prepareSearch()
//                    .setIndices("dcinsides")
//                    .setTypes("documents")
//                    .setFrom(0)
//                    .setSize(15)
//                    .setQuery(
//                            QueryBuilders.matchAllQuery()
//                    )
//                    .execute().actionGet();
//
//            for(SearchHit hit: allHits.getHits()){
//                System.out.println(hit.getSourceAsString());
//            }

//            AnalyzeRequest request = new AnalyzeRequest("은하8가려고 기다리는중이번주 토요일이 심히 기대된다진짜 ios 9.3.5 개좋았는데 삼디터치도 꽤 쓸만했고");
//
//            request.analyzer("openkoreantext-analyzer");
//
//            List<AnalyzeResponse.AnalyzeToken> tokens = client.admin().indices().analyze(request).actionGet().getTokens();
//
//            for(AnalyzeResponse.AnalyzeToken token: tokens) {
//                System.out.println(token);
//            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
