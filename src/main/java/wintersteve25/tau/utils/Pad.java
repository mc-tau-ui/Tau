package wintersteve25.tau.utils;

public class Pad {
    public final int top;
    public final int bottom;
    public final int left;
    public final int right;

    public Pad(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
    
    public Vector2i getSize() {
        return new Vector2i(left + right, top + bottom);
    }

    public static final class Builder {
        private int top;
        private int bottom;
        private int left;
        private int right;

        public Builder() {
        }
        
        public Builder withTop(int top) {
            this.top = top;
            return this;
        }

        public Builder withBottom(int bottom) {
            this.bottom = bottom;
            return this;
        }

        public Builder withLeft(int left) {
            this.left = left;
            return this;
        }

        public Builder withRight(int right) {
            this.right = right;
            return this;
        }
        
        public Builder all(int amount) {
            this.top = amount;
            this.bottom = amount;
            this.left = amount;
            this.right = amount;
            return this;
        }

        public Pad build() {
            return new Pad(top, bottom, left, right);
        }
    }
}
