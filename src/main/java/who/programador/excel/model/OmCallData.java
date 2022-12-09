package who.programador.excel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OmCallData {
    private String name;
    private Double value;
    private long totalTimeInMinute = 0L;

    public void addTimeInMinute(long time) {
        totalTimeInMinute += time;
    }

    public long calculateValue(long valueHour) {
        return valueHour * totalTimeInMinute / 60;
    }

}
