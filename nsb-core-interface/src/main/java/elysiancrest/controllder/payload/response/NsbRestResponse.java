package elysiancrest.controllder.payload.response;

import elysiancrest.controllder.payload.BaseNsbRestRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NsbRestResponse extends BaseNsbRestRequest {
    private Header header;
    private Body body;
}

