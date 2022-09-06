package kopo.poly.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.repository.NoticeRepository;
import kopo.poly.repository.entity.NoticeEntity;
import kopo.poly.service.INoticeService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("NoticeService")
public class NoticeService implements INoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDTO> getNoticeList() {
        List<NoticeEntity> rList = noticeRepository.findAllByOrderByNoticeSeqDesc();

        List<NoticeDTO> nList = new ObjectMapper().convertValue(rList,
                new TypeReference<List<NoticeDTO>>() {
                });

        return nList;
    }

    @Transactional
    @Override
    public NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) {

        if (type){
            int res = noticeRepository.updateReadCnt(pDTO.getNoticeSeq());

        }

        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(pDTO.getNoticeSeq());

        NoticeDTO rDTO = new ObjectMapper().convertValue(rEntity, NoticeDTO.class);

        return rDTO;

    }

    @Transactional
    @Override
    public void updateNoticeInfo(NoticeDTO pDTO){

        Long noticeSeq = pDTO.getNoticeSeq();

        String title = CmmUtil.nvl(pDTO.getTitle());
        String noticeYn = CmmUtil.nvl(pDTO.getNoticeYn());
        String contents = CmmUtil.nvl(pDTO.getContents());
        String userId = CmmUtil.nvl(pDTO.getUserId());

        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(noticeSeq);

        NoticeEntity pEntity = NoticeEntity.builder().noticeSeq(noticeSeq).title(title).noticeYn(noticeYn)
                .contents(contents).userId(userId).readCnt(rEntity.getReadCnt())
                .build();

        noticeRepository.save(pEntity);


    }

    @Override
    public void deleteNoticeInfo(NoticeDTO pDTO) throws Exception{

        Long noticeSeq = pDTO.getNoticeSeq();

        noticeRepository.deleteById(noticeSeq);

    }

    @Override
    public void InsertNoticeInfo(NoticeDTO pDTO) throws Exception{
        String title = CmmUtil.nvl(pDTO.getTitle());
        String noticeYn = CmmUtil.nvl(pDTO.getNoticeYn());
        String contents = CmmUtil.nvl(pDTO.getContents());
        String userId = CmmUtil.nvl(pDTO.getUserId());

        NoticeEntity pEntity = NoticeEntity.builder()
                .title(title).noticeYn(noticeYn).contents(contents).userId(userId).readCnt(0L)
                .regId(userId).regDT(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .chgId(userId).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .build();

        noticeRepository.save(pEntity);

    }


}
