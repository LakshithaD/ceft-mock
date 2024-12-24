package elysiancrest.controllder.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JpCancellationRequest extends BaseNsbRestRequest {

    private String channel;
    private String reference;
    private String particulars;
    private String returnReferenceNumber;
    private String stan;
    private String transactionType = "JP.CANCELLATION";

}
