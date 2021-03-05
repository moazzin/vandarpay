package ir.mzn.vandarpay.model;

import com.google.gson.annotations.SerializedName;

public class Step1Out {
    @SerializedName("status")
    public int status;

    @SerializedName("token")
    public String token;
}
