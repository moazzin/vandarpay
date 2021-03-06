package ir.mzn.vandarpay;

public enum Step {
    step1("https://ipg.vandar.io/api/v3/send"),
//    step2(null),
    step3("https://vandar.io/api/ipg/2step/transaction"),
    step4("https://ipg.vandar.io/api/v3/verify");

    final String url;

    Step(String url) {
        this.url = url;
    }
}
