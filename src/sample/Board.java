package sample;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import java.util.Random;

public class Board extends GridPane
{
    public Square[][] squares;
    private int mines;
    private int rows;
    private int columns;
    private int flagged;

    public Board(int ro, int co, int mi)
    {
        rows = ro;
        columns = co;
        mines = mi;
        squares = new Square[rows][columns];
        flagged = 0;

        // Sets up board
        // Assigns mouse functionality to each square for uncovering / flagging
        for (int c = 0; c < columns; c++)
        {
            for (int r = 0; r < rows; r++)
            {

                Square sq = new Square(r,c);
                sq.setOnMouseClicked(value-> {
                    if(value.getButton() == MouseButton.PRIMARY)
                    {
                        if (sq.checkSquare()) {
                            Main.setStartScene();
                        }
                        else
                        {
                            if(sq.surroundingMines == 0)
                            {
                                autoChecker(sq.posx,sq.posy);
                            }
                        }
                        checkWon();
                    }
                    else if (value.getButton() == MouseButton.SECONDARY)
                    {
                        if(sq.setFlag()) {flagged++;}
                        else{flagged--;}
                        checkWon();
                    }
                });
                this.add(sq,r,c);
            }
        }

        // Randomly assigns mines depending on how many required
        // Checks if mine is already assigned and will try again if so
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


    // Adds values to surrounding Mines indicator for each mine
    // For surrounding squares in bound
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

    // Function is called to check surrounding cells upon finding an empty cell
    public void autoChecker(int x, int y)
    {
        for(int i = -1; i<2; i++)
        {
            for(int j = -1; j<2; j++)
            {
                // checks if cell is out of bound or if middle cell (i=0 and j=0)
                if(x+i >= 0 && x+i < rows && y+j >= 0 && y+j < columns && !(i == 0 && j == 0))
                {
                    if(squares[x+i][y+j].checked == false)
                    {
                        if(squares[x+i][y+j].autoCheck() == 0)
                        {
                            autoChecker(x+i,y+j);
                        }
                    }
                }
            }
        }
    }

    // Upon winning, all squares to be shown
    public void showAll()
    {
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(squares[i][j].checked == false && squares[i][j].flag == false)
                {
                    squares[i][j].checkSquare();
                }
            }
        }
    }

    // Function validates if game has been won
    // Provided all mines are flagged and squares flagged correctly return true
    public boolean checkWon()
    {
        if (flagged == mines)
        {
            for(int i = 0; i < rows; i++)
            {
                for (int j = 0; j < columns; j++)
                {
                    if(squares[i][j].checkIncorrectlyFlagged())
                    {
                        return false;
                    }
                }
            }

            // Raise alert and return true
            showAll();
            Alert won = new Alert(Alert.AlertType.INFORMATION,"Congratulations! You won!");
            won.showAndWait();
            Main.setStartScene();
            return true;
        }
        return false;
    }

}
