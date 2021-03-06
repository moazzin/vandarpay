package ir.mzn.vandarpay.model;

import com.google.gson.annotations.SerializedName;
import ir.mzn.vandarpay.IGetResultType;

public class VerifyParameter implements IGetResultType<VerifyResult> {
    @SerializedName("api_key")
    public String apiKey;

    @SerializedName("token")
    public String token;

    @Override
    public Class<VerifyResult> getResultType() {
        return VerifyResult.class;
    }
}
