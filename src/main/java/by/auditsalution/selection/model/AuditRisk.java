package by.auditsalution.selection.model;

import lombok.Getter;

@Getter
public enum AuditRisk {

    LOW("Низкий"),
    AVERAGE("Средний"),
    HIGH("Высокий");

    private String description;
    AuditRisk(String description){
        this.description = description;
    }

}
