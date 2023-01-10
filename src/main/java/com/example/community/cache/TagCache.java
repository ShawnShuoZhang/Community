package com.example.community.cache;

import com.example.community.dto.TagDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 标签缓存
 *
 * @author Tuoer
 * @date 2023/01/10
 */
public class TagCache {
    /**
     * 得到
     *
     * @return {@link List}<{@link TagDto}>
     */
    public static List<TagDto> get() {
        List<TagDto> tagDtos = new ArrayList<>();
        TagDto program = new TagDto();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "node.js", "python", "java", "golang", "c++", "c", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDtos.add(program);
        TagDto framework = new TagDto();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDtos.add(framework);
        TagDto server = new TagDto();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "docker", "apache", "ubuntu", "centos", "缓存", "nginx", "负载均衡", "tomcat", "unix", "hadoop", "windows-server"));
        tagDtos.add(server);
        TagDto db = new TagDto();
        db.setCategoryName("数据库和缓存");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql", "memcached", "sqlserver", "postgresql", "sqlite"));
        tagDtos.add(db);
        TagDto tool = new TagDto();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom", "emacs", "textmate", "hg"));
        tagDtos.add(tool);
        return tagDtos;
    }

    /**
     * 过滤无效
     *
     * @param tags 标签
     * @return boolean
     */
    public static String filterInvalid(String tags) {
        String[] split = tags.split("，");
        List<TagDto> tagDtos = get();
        List<String> tagList = new ArrayList<>();
        tagDtos.forEach(tagDto -> tagList.addAll(tagDto.getTags()));
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining("，"));
        return invalid;
    }
}
