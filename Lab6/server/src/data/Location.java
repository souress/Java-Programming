package data;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = -2291942340555641293L;
    private long x;
    private float y;
    private Long z; //Поле не может быть null

    public Location(long x, float y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getLocationX() {
        return x;
    }

    public float getLocationY() {
        return y;
    }

    public Long getLocationZ() {
        return z;
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y +
                ", z=" + z;
    }
}
