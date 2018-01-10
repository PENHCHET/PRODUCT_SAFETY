package kr.ac.cbnu.bigdata.product_safety;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String args[]){
        try {
            Settings settings = Settings.builder().put("cluster.name", "Elasticsearch").build();
            PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings);

            String InetSocket[] = "192.168.28.129:9300".split(":");
            String address = InetSocket[0];
            Integer port = Integer.valueOf(InetSocket[1]);
            TransportClient transportClient = preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), port));
            System.out.println("CONNECTION SUCCESSFULLY...");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
