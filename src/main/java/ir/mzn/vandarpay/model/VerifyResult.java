package ir.mzn.vandarpay.model;

import com.google.gson.annotations.SerializedName;

public class VerifyResult {
    @SerializedName("status")
    public int status;

    @SerializedName("amount")
    public String amount;

    @SerializedName("transId")
    public Long transactionId;


}
