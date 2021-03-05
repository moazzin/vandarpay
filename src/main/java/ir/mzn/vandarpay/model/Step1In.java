package ir.mzn.vandarpay.model;

import com.google.gson.annotations.SerializedName;

public class Step1In {
    @SerializedName("api_key")
    public String apiKey;

    @SerializedName("amount")
    public long amount;

    @SerializedName("callback_url")
    public String callbackUrl;
}
