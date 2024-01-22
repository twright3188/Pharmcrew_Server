package kr.ant.kpa.pharmcrew.sms.impl;

import com.bumdori.spring.BLogger;
import kr.ant.kpa.pharmcrew.sms.SmsSender;
import kr.ant.kpa.pharmcrew.sms.exception.SmsException;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;

@Component
public class SmsSenderImpl implements SmsSender {
    private static final Logger logger = LoggerFactory.getLogger(SmsSenderImpl.class);

    private static final String ID = "antservice";
    private static final String PWD = "ptax1215";
    private static final String SENDER = "07086560707";

    private final SmsApiService service;

    @Override
    public void send(String to, String body) throws SmsException {
        try {
            Response<String> response = service.send(ID, PWD, 3, to, SENDER, body).execute();
            if (response.isSuccessful()) {
                BLogger.debug(logger, "resp: {}", response.body());
            } else {
                SmsException e = new SmsException();
                e.setMessage(String.format("error: {}", response.errorBody().string()));
                throw e;
            }
        } catch (IOException e) {
            e.printStackTrace();
            SmsException e1 = new SmsException();
            e1.setMessage(String.format("error: {}", e1.getLocalizedMessage()));
            throw e1;
        }
    }

    public interface SmsApiService {
        @GET("mssendUTF.asp")
        Call<String> send(
                @Query("uid") String uid,
                @Query("pwd") String pwd,
                @Query("sendType") int sendType,
                @Query("toNumber") String to,
                @Query("fromNumber") String from,
                @Query("contents") String body);
    }

    public SmsSenderImpl() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                BLogger.debug(logger, "smsSender: {}", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        service = new Retrofit.Builder()
                .baseUrl("http://biz.moashot.com/EXT/URLASP/")
                .client(new OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .build()
                )
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(SmsApiService.class);
    }
}
