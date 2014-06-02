package com.cn.template.repository.weixin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.weixin.NewsCategory;

/**
 *图文消息类别的数据访问接口.
 * @author Libra
 *
 */
public interface NewsCategoryDao extends PagingAndSortingRepository<NewsCategory, Long>, JpaSpecificationExecutor<NewsCategory>{

}
