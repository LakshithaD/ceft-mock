package elysiancrest.controllder.payload.response;

import elysiancrest.controllder.payload.BaseNsbRestRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Header extends BaseNsbRestRequest {
    private String transactionStatus;

    private String status;
    private String id;
    private Audit audit;

    private String uniqueIdentifier;
}
