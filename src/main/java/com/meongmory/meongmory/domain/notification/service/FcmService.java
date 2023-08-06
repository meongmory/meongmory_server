package com.meongmory.meongmory.domain.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.meongmory.meongmory.domain.notification.dto.FcmMessage;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmService {

    private final String MEDIA_TYPE = "application/json; charset=utf-8";
    private final String AUTHORIZATION_NAME = "Bearer ";
    private final String CONTENT_TYPE = "application/json; UTF-8";
    private final ObjectMapper objectMapper;

    @Value("${firebase.api-url}")
    private String API_URL;

    @Value("${firebase.path}")
    private String CONFIG_PATH;

    @Value("${firebase.scopes}")
    private String SCOPES;

    public Response sendMessage(String deviceToken, String title, String content) throws IOException {
        OkHttpClient client = new OkHttpClient();
        FcmMessage message = FcmMessage.makeMessage(deviceToken, title, content);
        RequestBody requestBody = RequestBody.create(objectMapper.writeValueAsString(message),
                MediaType.get(MEDIA_TYPE));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, AUTHORIZATION_NAME + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE)
                .build();

        return client.newCall(request).execute();
    }

    private String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(CONFIG_PATH).getInputStream())
                .createScoped(List.of(SCOPES));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

}
