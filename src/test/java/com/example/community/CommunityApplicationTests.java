package com.example.community;

import com.example.community.dto.TagDto;
import com.example.community.mapper.QuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
        List<TagDto> tagDtos = new ArrayList<>();
        TagDto program = new TagDto();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "node.js", "python", "java", "golang", "c++", "c", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));

        TagDto framework = new TagDto();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));

        TagDto server = new TagDto();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "docker", "apache", "ubuntu", "centos", "缓存", "nginx", "负载均衡", "tomcat", "unix", "hadoop", "windows-server"));

        TagDto db = new TagDto();
        db.setCategoryName("数据库和缓存");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql", "memcached", "sqlserver", "postgresql", "sqlite"));

        TagDto tool = new TagDto();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom", "emacs", "textmate", "hg"));

        redisTemplate.opsForList().leftPush("tagDtos", program);
        redisTemplate.opsForList().leftPush("tagDtos", framework);
        redisTemplate.opsForList().leftPush("tagDtos", server);
        redisTemplate.opsForList().leftPush("tagDtos", db);
        redisTemplate.opsForList().leftPush("tagDtos", tool);
    }

    @Test
    public void resdTest() {
        List<TagDto> tagDtos = new ArrayList<>();
        List<Object> tagDtos1 = redisTemplate.opsForList().range("tagDtos", 0, -1);
        for (Object tagDto : tagDtos1) {
            tagDtos.add((TagDto) tagDto);
        }
        System.out.println(tagDtos);
    }
}
