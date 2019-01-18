package com.ps.server.service;

import com.ps.server.entity.RankingEntity;
import com.ps.server.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    public Page<RankingEntity> findPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<RankingEntity> allRanking = rankingRepository.findAll();
        List<RankingEntity> list;

        if (allRanking.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allRanking.size());
            list = allRanking.subList(startItem, toIndex);
        }

        Page<RankingEntity> rankingEntityPage
                = new PageImpl<RankingEntity>(list, PageRequest.of(currentPage, pageSize), allRanking.size());

        return rankingEntityPage;
    }
}
