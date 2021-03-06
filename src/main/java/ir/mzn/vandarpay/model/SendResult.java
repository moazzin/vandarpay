package ir.mzn.vandarpay.model;

import com.google.gson.annotations.SerializedName;

public class SendResult {
    @SerializedName("status")
    public int status;

    @SerializedName("token")
    public String token;
}
