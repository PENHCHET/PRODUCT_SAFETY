package kr.ac.cbnu.bigdata.product_safety.services;

import kr.ac.cbnu.bigdata.product_safety.models.DCInsideContent;

import java.util.List;

/**
 * Created by HP1 on 2018-03-20.
 */
public interface DCInsideContentService {
    public List<DCInsideContent> getAllDcInsideContents();

    public boolean addAllDcInsideContentsIntoElasticsearch();
}
