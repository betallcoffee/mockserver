package org.mockserver.client.serialization.model;

import org.mockserver.model.Not;
import org.mockserver.model.Parameter;

/**
 * @author jamesdbloom
 */
public class ParameterDTO extends KeyToMultiValueDTO {

    public ParameterDTO(Parameter parameter) {
        super(parameter);
    }

    protected ParameterDTO() {
    }

    public Parameter buildObject() {
        return new Parameter(getName(), getValues());
    }
}
