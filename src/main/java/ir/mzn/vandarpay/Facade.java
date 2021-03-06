package ir.mzn.vandarpay;

import java.util.Optional;
import java.util.function.Consumer;

import static ir.mzn.vandarpay.VandarPayTemplate.IMPL;

public class Facade {

    public static String payKey = "vandar_pay";
    public static String apiKey = "";
    public static int expirePayKey = 3600;

    /**
     *
     * @param amount
     * @param url: callback url
     * @return return token it every thing is ok
     */
    public Optional<String> begin(long amount, String url) {
        return IMPL.doStep1(amount, url);
    }

    /**
     *
     * @param token
     * @return return transaction id if every thing is ok
     */
    public Optional<Long> end(String token) {
        Optional<Long> result = IMPL.doStep3(token);
        if (result.isPresent() && IMPL.doStep4(token, result.get())) {
            return result;
        } else {
            return Optional.empty();
        }
    }
}
