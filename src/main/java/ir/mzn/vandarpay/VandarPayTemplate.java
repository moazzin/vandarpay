package ir.mzn.vandarpay;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.io.IOException;
import java.util.Optional;

public interface VandarPayTemplate {
    String APP_JSON = "application/json";
    MediaType JSON_MEDIA_TYPE = MediaType.parse(APP_JSON);
    VandarPayTemplate IMPL = new VandarPayTemplateImp();
    Jedis jedis = new Jedis();

    Optional<String> doStep1(long amount, String url);
//    String doStep2();
    Optional<Long> doStep3(String token);
    boolean doStep4(String token, Long transactionId);

    default String getKey(String token) {
        return Facade.payKey +
                ':' +
                token;
    }

    default void put(String token, String amount) {
        jedis.set(getKey(token), amount, SetParams.setParams().ex(Facade.expirePayKey));
    }

    default String get(String token) {
        String result = "";
        String key = getKey(token);
        if (jedis.exists(key)) {
            result = jedis.get(key);
            jedis.del(key);
        }
        return result;
    }

    default <P extends IGetResultType<R>, R> R execute(P param, Step step) throws IOException, IllegalAccessException, InstantiationException {
        Gson gson = new Gson();
        String json = gson.toJson(param);
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, json);
        Request request = new Request.Builder()
                .url(step.url)
                .post(body)
                .addHeader("Accept", APP_JSON)
                .addHeader("Content-Type", APP_JSON)
                .build();
        Response response = new OkHttpClient().newCall(request).execute();

        return response.isSuccessful()?
                gson.fromJson(response.body().string(), param.getResultType()):
                param.getResultType().newInstance();
    }
}
