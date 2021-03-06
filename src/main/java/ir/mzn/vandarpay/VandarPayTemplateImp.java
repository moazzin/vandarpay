package ir.mzn.vandarpay;

import ir.mzn.vandarpay.model.SendParameter;
import ir.mzn.vandarpay.model.VerifyParameter;
import ir.mzn.vandarpay.model.SendResult;
import ir.mzn.vandarpay.model.VerifyResult;

import java.util.Objects;
import java.util.Optional;

import static ir.mzn.vandarpay.Step.*;

public class VandarPayTemplateImp implements VandarPayTemplate {


    @Override
    public Optional<String> doStep1(long amount, String callbackUrl) {
        try {
            SendParameter in = new SendParameter();
            in.apiKey = Facade.apiKey;
            in.amount = amount;
            in.callbackUrl = callbackUrl;

            SendResult out = execute(in, step1);

            if (out.status == 1) {
                put(out.token, String.valueOf(amount));
                return Optional.of(out.token);
            }
        } catch (Exception ignored) {

        }
        return Optional.empty();
    }

//    @Override
//    public String doStep2() {
//        return null;
//    }

    @Override
    public Optional<Long> doStep3(String token) {
        try {
            VerifyParameter in = new VerifyParameter();
            in.apiKey = Facade.apiKey;
            in.token = token;

            VerifyResult out = execute(in, step3);

            String realAmount = get(token);

            if (Objects.nonNull(realAmount) &&
                    out.status == 1 &&
                    out.amount.equals(realAmount)) {
                return Optional.of(out.transactionId);
            }
        } catch (Exception ignored) {

        }
        return Optional.empty();
    }

    @Override
    public boolean doStep4(String token, Long transactionId) {
        try {
            VerifyParameter in = new VerifyParameter();
            in.apiKey = Facade.apiKey;
            in.token = token;

            VerifyResult out = execute(in, step4);

            return out.status == 1 && out.transactionId.equals(transactionId);
        } catch (Exception e) {
            return false;
        }
    }


}
