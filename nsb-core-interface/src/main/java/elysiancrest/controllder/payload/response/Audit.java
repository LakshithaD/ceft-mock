package elysiancrest.controllder.payload.response;

import elysiancrest.controllder.payload.BaseNsbRestRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Audit extends BaseNsbRestRequest {
    private Double T24_time;
    private Double responseParse_time;
    private Double requestParse_time;
    private Double versionNumber;

}
