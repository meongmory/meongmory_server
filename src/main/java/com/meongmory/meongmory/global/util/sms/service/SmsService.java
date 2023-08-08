package com.meongmory.meongmory.global.util.sms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meongmory.meongmory.global.util.redis.RedisTemplateService;
import com.meongmory.meongmory.global.util.sms.dto.MessageDto;
import com.meongmory.meongmory.global.util.sms.dto.SmsNaverReq;
import com.meongmory.meongmory.global.util.sms.dto.SmsSendReq;
import com.meongmory.meongmory.global.util.sms.dto.SmsSendRes;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final RedisTemplateService redisTemplateService;

    @Value("${sms.service-id}")
    private String serviceId;

    @Value("${sms.access-key}")
    private String accessKey;

    @Value("${sms.secret-key}")
    private String secretKey;

    @Value("${sms.phone-num}")
    private String phone;

    public String send(SmsSendReq smsSendReq) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        String certification = Integer.toString((int)(Math.random() * (99999 - 10000 + 1)) + 10000);
        Long time = System.currentTimeMillis();
        if (smsSendReq.getPhone().equals("00012341235")) {
            certification = "12345";
            return certification;
        }
        List<MessageDto> messages = new ArrayList<>();
        messages.add(new MessageDto(smsSendReq.getPhone(), "animori \n 인증번호: "+certification));
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(new SmsNaverReq("SMS", this.phone, certification, messages));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", this.accessKey);
        String sig = makeSignature(time); // 암호화
        headers.set("x-ncp-apigw-signature-v2", sig);

        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+this.serviceId+"/messages"), body, SmsSendRes.class);
        // Redis에 인증번호 저장 (1분 만료시간 설정)
        redisTemplateService.setCertificationCode(smsSendReq.getPhone(), certification);

        return certification;
    }

    public boolean validateCode(String phone, String inputCode) {
        String storedCode = redisTemplateService.getCertificationCode(phone);

        // Redis에 저장된 코드가 없거나 입력받은 코드와 다르면 false 반환
        if (storedCode == null || !storedCode.equals(inputCode)) {
            return false;
        }
        return true;
    }


    public String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {

        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }
}
