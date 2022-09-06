package kopo.poly.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoticeDTO {

    private Long noticeSeq;
    private String title;
    private String noticeYn;
    private String contents;
    private String userId;
    private String readCnt;
    private String regId;
    private String regDt;
    private String chgId;
    private String chgDt;
    private String userName;

}
