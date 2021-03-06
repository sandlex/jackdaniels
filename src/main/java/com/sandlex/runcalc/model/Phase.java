package com.sandlex.runcalc.model;

import lombok.Value;
import org.apache.commons.lang.StringUtils;

@Value
public class Phase implements Calculable {

    Measure measure;
    PaceName paceName;

    public Phase(String input) {
        boolean isDurationMeasure = input.contains(":");

        String phase = StringUtils.remove(input, ".");
        phase = StringUtils.remove(phase, ":");

        int paceNameIndex = 1;
        while (StringUtils.isNumeric(phase.substring(0, paceNameIndex))) {
            paceNameIndex++;
        }

        String paceNameInput = phase.substring(paceNameIndex - 1);
        paceName = new PaceName(paceNameInput);
        int paceNameLength = paceNameInput.length();
        String originalMeasureInput = input.substring(0, input.length() - paceNameLength);
        measure = isDurationMeasure ? new Duration(originalMeasureInput) : new Distance(originalMeasureInput);
    }

}
