package com.example.community.cache;

import com.example.community.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component

/**
 * 标签缓存
 *
 * @author Tuoer
 * @date 2023/01/10
 */
public class Tag {
    @Autowired

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 得到
     *
     * @return {@link List}<{@link TagDto}>
     */
    public List<TagDto> get() {
        List<TagDto> tagDtos = new ArrayList<>();
        List<Object> tagDtos1 = redisTemplate.opsForList().range("tagDtos", 0, -1);
        assert tagDtos1 != null;
        for (Object tagDto : tagDtos1) {
            tagDtos.add((TagDto) tagDto);
        }
        return tagDtos;
    }

    /**
     * 过滤无效
     *
     * @param tags 标签
     * @return boolean
     */
    public String filterInvalid(String tags) {
        String[] split = tags.split("，");
        List<TagDto> tagDtos = get();
        List<String> tagList = new ArrayList<>();
        tagDtos.forEach(tagDto -> tagList.addAll(tagDto.getTags()));
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining("，"));
        return invalid;
    }
}
