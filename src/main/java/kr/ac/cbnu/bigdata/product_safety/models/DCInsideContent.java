package kr.ac.cbnu.bigdata.product_safety.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by HP1 on 2018-03-20.
 */
public class DCInsideContent {
    @JsonProperty("dc_cont_index")
    private int dcContIndex;
    @JsonProperty("board_num")
    private int boardNum;
    @JsonProperty("board_title")
    private String boardTitle;
    @JsonProperty("writer_id")
    private String writerId;
    @JsonProperty("content")
    private String content;
    @JsonProperty("content_date")
    private Date contentDate;
    @JsonProperty("insert_date")
    private Date insertDate;

    public int getDcContIndex() {
        return dcContIndex;
    }

    public void setDcContIndex(int dcContIndex) {
        this.dcContIndex = dcContIndex;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getContentDate() {
        return contentDate;
    }

    public void setContentDate(Date contentDate) {
        this.contentDate = contentDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public String toString() {
        return "DCInsideContentRepository{" +
                "dcContIndex=" + dcContIndex +
                ", boardNum=" + boardNum +
                ", boardTitle='" + boardTitle + '\'' +
                ", writerId='" + writerId + '\'' +
                ", content='" + content + '\'' +
                ", contentDate=" + contentDate +
                ", insertDate=" + insertDate +
                '}';
    }
}
