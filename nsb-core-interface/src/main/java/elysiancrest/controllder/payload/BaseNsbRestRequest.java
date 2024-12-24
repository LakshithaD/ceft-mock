package elysiancrest.controllder.payload;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Getter
@Setter
public abstract class BaseNsbRestRequest {

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString( this, ToStringStyle.JSON_STYLE);
    }

}
