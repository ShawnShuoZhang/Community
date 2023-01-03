package com.example.community.provider;

import com.alibaba.fastjson.JSON;
import com.example.community.dto.AccessTokenDto;
import com.example.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * github提供者
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Component
public class GithubProvider {
    /**
     * 获取访问令牌
     *
     * @param accessTokenDto 访问令牌dto
     * @return {@link String}
     */
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType JSON1 = MediaType.get("application/json");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto), JSON1);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            String[] split = string.split("&");
            String tokenstr = split[0];
            String token = tokenstr.split("=")[1];
            return token;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取用户
     *
     * @param accessToken 访问令牌
     * @return {@link GithubUser}
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            System.out.println(githubUser.getAvatarUrl());
            System.out.println(githubUser.toString());
            return githubUser;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
