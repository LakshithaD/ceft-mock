package elysiancrest.controllder.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@RequiredArgsConstructor
public class NsbRestRequestWrapper<T>  {

    private final T body;

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString( this, ToStringStyle.JSON_STYLE);
    }


}
