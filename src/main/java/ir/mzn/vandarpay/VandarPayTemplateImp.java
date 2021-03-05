package ir.mzn.vandarpay;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import ir.mzn.vandarpay.model.Step1In;
import ir.mzn.vandarpay.model.Step1Out;

import java.io.IOException;
import java.util.Optional;

import static ir.mzn.vandarpay.Step.step1;

public class VandarPayTemplateImp implements VandarPayTemplate {
    private static final String APP_JSON = "application/json";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse(APP_JSON);

    @Override
    public Optional<String> doStep1(long amount, String callbackUrl) {
        try {
            Step1In in = new Step1In();
            in.apiKey = Facade.api_key;
            in.amount = amount;
            in.callbackUrl = callbackUrl;

            Step1Out out = execute(in, step1, Step1Out.class);

            return out.status == 1 ?
                    Optional.of(out.token):
                    Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public String doStep2() {
        return null;
    }

    @Override
    public void doStep3(String token) {

    }

    @Override
    public boolean doStep4() {
        return false;
    }

    <P, R> R execute(P param, Step step, Class<R> resultType) throws IOException, IllegalAccessException, InstantiationException {
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
            gson.fromJson(response.body().string(), resultType):
            resultType.newInstance();
    }
}
