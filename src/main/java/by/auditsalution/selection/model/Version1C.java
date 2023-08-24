package by.auditsalution.selection.model;

import lombok.Getter;

@Getter
public enum Version1C {

    VERSION_1C_7("1C7"),
    VERSION_1C_8("1C8");
    private String description;
    Version1C(String description){
        this.description = description;
    }

}
