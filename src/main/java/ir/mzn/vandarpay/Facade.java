package ir.mzn.vandarpay;

import static ir.mzn.vandarpay.VandarPayTemplate.IMPL;

public class Facade {

    public static String payKey = "vandar_pay";
    public static String api_key = "";

    public String begin(long amount, String url) {
        r1 = IMPL.doStep1(amount, url);
//        if r1
        return IMPL.doStep2(r1);
    }

    public boolean end(String token) {
        r3 = IMPL.doStep3();
//        if (r3)
        return IMPL.doStep4();
    }
}
