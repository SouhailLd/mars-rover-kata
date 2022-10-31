package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DirectionEnum {
    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W');

    private final int value;
    private final char shortName;

    public DirectionEnum getBackwardDirection() {
        return values()[(this.getValue() + 2) % 4];
    }
}
