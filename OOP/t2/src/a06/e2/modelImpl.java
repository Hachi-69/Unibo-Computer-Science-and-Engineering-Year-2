package a06.e2;

import java.util.Random;

public class modelImpl implements model {

    private int counter = 0;
    private int prev = 0;

    @Override
    public int createRandomNumbers() {
        return new Random().nextInt(1, 6);
    }

    @Override
    public Boolean isEqual(Integer num) {
        if (this.prev == 0) {
            this.prev = num;
        } else {
            if (this.prev == num) {
                this.prev = 0;
                return true;
            } else {
                this.prev = 0;
            }
        }
        return false;
    }

    @Override
    public Boolean hideAfterTwo() {
        if (this.counter == 2) {
            this.counter = 0;
            return true;
        } else {
            this.counter++;
            return false;
        }
    }

}
