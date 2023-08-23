package sample;

import javafx.scene.layout.GridPane;
import java.util.Random;

public class Board extends GridPane
{
    public Square[][] squares;
    private int mines = 5;
    private int rows;
    private int columns;

    public Board(int ro, int co)
    {
        rows = ro;
        columns = co;
        squares = new Square[rows][columns];

        for (int c = 0; c < columns; c++)
        {
            for (int r = 0; r < rows; r++)
            {

                Square sq = new Square(r,c);
                sq.setOnAction(value-> {
                    if (sq.checkSquare()) {
                        Main.setStartScene();
                    }
                });
                this.add(sq,r,c);
            }
        }
        int minesAssign = mines;
        while(minesAssign != 0)
        {
            Random rand = new Random();
            int randrow = rand.nextInt(rows);
            int randcolumn = rand.nextInt(columns);
            if(!squares[randrow][randcolumn].getMine())
            {
                squares[randrow][randcolumn].setMine();
                minesAssign--;
                addMine(randrow,randcolumn);
            }
        }
    }

    public void add(Square s, int r, int c)
    {
        squares[r][c] = s;
        super.add(squares[r][c],c,r);
    }

    public void addMine(int x, int y)
    {
        for(int i = -1; i<2; i++)
        {
            for(int j = -1; j<2; j++)
            {
                if(x+i >= 0 && x+i < rows && y+j >= 0 && y+j < columns && !(i == 0 && j == 0))
                {
                    squares[x+i][y+j].surroundingMines++;
                }
            }
        }
    }
}
