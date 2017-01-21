package secretprojectstudios.domain;

import org.apache.commons.lang3.StringUtils;

public enum Ideal {
    Unknown,
    Health,
    Education,
    Economy,
    Military,
    Police,
    Science,
    Infrastructure,
    Religion,
    Entertainment,
    Environment,
    Food;

    public static Ideal fromString(String source) {
        String clean = StringUtils.trimToNull(source);
        if (clean == null)
            return Unknown;
        for (Ideal ideal: values()) {
            if (ideal.toString().equalsIgnoreCase(clean))
                return ideal;
        }
        return Unknown;
    }
}