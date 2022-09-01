package kopo.poly.service;

import kopo.poly.dto.NoticeDTO;

import java.util.List;

public interface INoticeService {

    List<NoticeDTO> getNoticeList();

    NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) throws Exception;

    void updateNoticeInfo(NoticeDTO pDTO) throws Exception;

    void deleteNoticeInfo(NoticeDTO pDTO) throws Exception;

    void InsertNoticeInfo(NoticeDTO pDTO) throws Exception;

}
