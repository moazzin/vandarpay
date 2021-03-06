package ir.mzn.vandarpay.model;

import com.google.gson.annotations.SerializedName;
import ir.mzn.vandarpay.IGetResultType;

public class SendParameter implements IGetResultType<SendResult> {
    @SerializedName("api_key")
    public String apiKey;

    @SerializedName("amount")
    public long amount;

    @SerializedName("callback_url")
    public String callbackUrl;

    @Override
    public Class<SendResult> getResultType() {
        return SendResult.class;
    }
}
