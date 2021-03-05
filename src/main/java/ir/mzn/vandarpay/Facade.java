package ir.mzn.vandarpay;

import java.util.Optional;

import static ir.mzn.vandarpay.VandarPayTemplate.IMPL;

public class Facade {

    public static String payKey = "vandar_pay";
    public static String api_key = "";

    public Optional<String> begin(long amount, String url) {
        return IMPL.doStep1(amount, url);
    }

    public boolean end(String token) {
        r3 = IMPL.doStep3();
//        if (r3)
        return IMPL.doStep4();
    }
}
