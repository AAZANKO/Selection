package by.auditsalution.selection.model;

import lombok.Getter;

@Getter
public enum InputOutputType {

    INPUT("in"),
    OUTPUT("out");
    private String description;
    InputOutputType(String description){
        this.description = description;
    }


}
