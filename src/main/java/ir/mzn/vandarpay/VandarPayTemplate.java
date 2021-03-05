package ir.mzn.vandarpay;

import java.util.Optional;

public interface VandarPayTemplate {

    VandarPayTemplate IMPL = new VandarPayTemplateImp();

    Optional<String> doStep1(long amount, String url);

    String doStep2();

    void doStep3(String token);

    boolean doStep4();
}
