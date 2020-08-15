package io.smallrye.openapi.jaxrs;

import static javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA;

import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.jboss.jandex.DotName;

import io.smallrye.openapi.runtime.scanner.spi.FrameworkParameter;

/**
 * Meta information for the JAX-RS *Param annotations relating them
 * to the In and Style attributes of Parameters.
 *
 * @author Michael Edgar {@literal <michael@xlate.io>}
 */
public enum JaxRsParameter {
    PATH_PARAM(JaxRsConstants.PATH_PARAM, Parameter.In.PATH, null, Parameter.Style.SIMPLE),
    // Apply to the last-matched @Path of the structure injecting the MatrixParam
    MATRIX_PARAM(JaxRsConstants.MATRIX_PARAM, Parameter.In.PATH, Parameter.Style.MATRIX, Parameter.Style.MATRIX),
    QUERY_PARAM(JaxRsConstants.QUERY_PARAM, Parameter.In.QUERY, null, Parameter.Style.FORM),
    FORM_PARAM(JaxRsConstants.FORM_PARAM, null, Parameter.Style.FORM, Parameter.Style.FORM),
    HEADER_PARAM(JaxRsConstants.HEADER_PARAM, Parameter.In.HEADER, null, Parameter.Style.SIMPLE),
    COOKIE_PARAM(JaxRsConstants.COOKIE_PARAM, Parameter.In.COOKIE, null, Parameter.Style.FORM),
    BEAN_PARAM(JaxRsConstants.BEAN_PARAM, null, null, null),

    // Support RESTEasy annotations directly
    RESTEASY_PATH_PARAM(RestEasyConstants.PATH_PARAM, Parameter.In.PATH, null, Parameter.Style.SIMPLE),
    // Apply to the last-matched @Path of the structure injecting the MatrixParam
    RESTEASY_MATRIX_PARAM(RestEasyConstants.MATRIX_PARAM, Parameter.In.PATH, Parameter.Style.MATRIX, Parameter.Style.MATRIX),
    RESTEASY_QUERY_PARAM(RestEasyConstants.QUERY_PARAM, Parameter.In.QUERY, null, Parameter.Style.FORM),
    RESTEASY_FORM_PARAM(RestEasyConstants.FORM_PARAM, null, Parameter.Style.FORM, Parameter.Style.FORM),
    RESTEASY_HEADER_PARAM(RestEasyConstants.HEADER_PARAM, Parameter.In.HEADER, null, Parameter.Style.SIMPLE),
    RESTEASY_COOKIE_PARAM(RestEasyConstants.COOKIE_PARAM, Parameter.In.COOKIE, null, Parameter.Style.FORM),
    RESTEASY_MULITIPART_FORM(RestEasyConstants.MULTIPART_FORM, null, null, null, MULTIPART_FORM_DATA);

    final FrameworkParameter parameter;

    private JaxRsParameter(DotName name, Parameter.In location, Parameter.Style style, Parameter.Style defaultStyle,
            String mediaType) {
        this.parameter = new FrameworkParameter(name, location, style, defaultStyle, mediaType);
    }

    private JaxRsParameter(DotName name, Parameter.In location, Parameter.Style style, Parameter.Style defaultStyle) {
        this(name, location, style, defaultStyle, null);
    }

    static FrameworkParameter forName(DotName annotationName) {
        for (JaxRsParameter value : values()) {
            if (value.parameter.getName().equals(annotationName)) {
                return value.parameter;
            }
        }
        return null;
    }

    public static boolean isParameter(DotName annotationName) {
        return forName(annotationName) != null;
    }
}
