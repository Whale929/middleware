package com.debug.middleware.server.service;

import com.debug.middleware.model.dto.PraiseRankDto;

import java.util.List;


public interface IRedisPraise {
    //缓存当前用户点赞博客的记录-包括正常点赞、取消点赞
    void cachePraiseBlog(Integer blogId, Integer uId, Integer status) throws Exception;
    //获取当前博客总的点赞数
    Long getCacheTotalBlog(Integer blogId) throws Exception;

    //触发博客点赞总数排行榜
    void rankBlogPraise() throws Exception;
    //获得博客点赞排行榜
    List<PraiseRankDto> getBlogPraiseRank() throws Exception;
}




















