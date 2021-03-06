package lermitage.intellij.extra.icons;

import org.jetbrains.annotations.Contract;

public enum ModelType {
    FILE("File"),
    DIR("Directory");


    private final String friendlyName;

    ModelType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * {@link ModelType} comparator: {@link ModelType#DIR} before {@link ModelType#FILE}.
     */
    @Contract(pure = true)
    public static int compare(ModelType o1, ModelType o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        if (o1 == DIR && o2 == FILE) {
            return -1;
        }
        return 1;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public static ModelType getByFriendlyName(String friendlyName) {
        for (ModelType modelType : ModelType.values()) {
            if (modelType.friendlyName.equals(friendlyName)) return modelType;
        }
        return null;
    }
}
