package com.ps.server.controller;

import com.ps.server.entity.RankingEntity;
import com.ps.server.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    @Autowired
    private RankingService service;

    /**
     * Returns ranking in paginated way.
     *
     * @param page number of page from ranking
     * @param size number of records per page
     * @return List of RankingEntity for given parameters
     */
    @RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
    public List<RankingEntity> findPaginated(
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<RankingEntity> resultPage = service.findPaginated(currentPage - 1, pageSize);

        int totalPages = resultPage.getTotalPages();


        if (page.get() > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException();
        }

        return resultPage.getContent();
    }

}
