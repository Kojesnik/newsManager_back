package com.epam.lab.repository;

import com.epam.lab.model.impl.News;
import com.epam.lab.model.impl.NewsCriteria;

public interface NewsRepository extends EntityRepository<News> {

    Long getCount();
    long getCriteriaCount(NewsCriteria newsCriteria);

}
