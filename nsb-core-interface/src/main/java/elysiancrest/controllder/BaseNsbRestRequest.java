package elysiancrest.controllder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public abstract class BaseNsbRestRequest {

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString( this, ToStringStyle.JSON_STYLE);
    }

}
