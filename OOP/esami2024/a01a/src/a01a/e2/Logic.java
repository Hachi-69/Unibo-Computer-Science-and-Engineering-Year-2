package a01a.e2;

public interface Logic {
    boolean selected(Position p);

    void calculatePerimeter();

    String getCellContent(Position p);
}
