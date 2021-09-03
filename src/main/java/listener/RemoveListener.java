package listener;

import lombok.Data;

@Data
public class RemoveListener {
    private String field;

    public RemoveListener(int field) {
        this.field = field + "has been removed";
    }
}
