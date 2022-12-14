package cinema;


public class Seat  {
    private int row;
    private int column;
    private int price;


    public Seat() {}
    public Seat (int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10: 8;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getPrice() {
        return price;
    }


    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
