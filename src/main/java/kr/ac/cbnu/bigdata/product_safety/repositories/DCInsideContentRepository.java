package kr.ac.cbnu.bigdata.product_safety.repositories;

import kr.ac.cbnu.bigdata.product_safety.models.DCInsideContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP1 on 2018-03-20.
 */
@Repository
public interface DCInsideContentRepository {

    public List<DCInsideContent> getAllDCInsideContents();
}
