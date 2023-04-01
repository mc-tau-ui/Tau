package wintersteve25.tau.layout;

@FunctionalInterface
public interface LayoutSetting {
    LayoutSetting START = (maxLength, componentLength) -> 0;
    LayoutSetting CENTER = (maxLength, componentLength) -> (maxLength - componentLength) / 2;
    LayoutSetting END = (maxLength, componentLength) -> maxLength - componentLength;

    int place(int maxLength, int componentLength);
}
