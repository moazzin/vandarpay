package ir.mzn.vandarpay;

public enum Step {
    step1("https://ipg.vandar.io/api/v3/send"),
//    step2(null),
    step3,
    step4;

    final String url;

    Step(String url) {
        this.url = url;
    }
}
