package com.epam.lab.service;

import com.epam.lab.dto.impl.NewsCriteriaDto;
import com.epam.lab.dto.impl.NewsDto;
import java.util.List;

public interface NewsService extends EntityService<NewsDto> {

    List<NewsDto> getByCriteria(NewsCriteriaDto newsCriteriaDto);
    Long getCount();
    Long getCriteriaCount(NewsCriteriaDto newsCriteriaDto);

}
