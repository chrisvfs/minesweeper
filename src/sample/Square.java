package sample;

import javafx.scene.control.Button;
import javafx.geometry.*;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;

public class Square extends Button {
    public int posx;
    public int posy;
    private boolean isMine;
    public int surroundingMines;
    public boolean checked;
    public boolean flag;


    // Sets variables and Square's styling information
    public Square(int x, int y)
    {
        //Square.super.setText(Integer.toString(x)+" "+Integer.toString(y));
        posx = x;
        posy = y;
        isMine = false;
        checked = false;
        Square.super.setMinWidth(25);
        Square.super.setMinHeight(25);
        Square.super.setAlignment(Pos.CENTER);
        Square.super.setFont(Font.font(11));

        surroundingMines = 0;
    }

    // Functionality for manual check
    // Displays alert when mine is checked
    public boolean checkSquare(){
        checked = true;
        super.setDisabled(true);
        if(isMine==true)
        {
            super.setText("*");
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Sorry! You hit a mine!");
            a.showAndWait();
        }
        else
        {
            if (surroundingMines > 0) { super.setText(Integer.toString(surroundingMines));}
            else { super.setText("");}
        }
        return isMine;
    }

    // Functionality for Board's autochecker
    // Returns surrounding mines for recursive call check
    public int autoCheck()
    {
        super.setDisabled(true);
        checked = true;
        if (surroundingMines > 0) { super.setText(Integer.toString(surroundingMines));}
        else { super.setText("");}
        return surroundingMines;
    }

    // Sets square to mine
    public void setMine(){
        isMine = true;
    }

    // Checks if mine
    public boolean getMine(){
        return isMine;
    }

    // Flags or unflags square returning new state
    public boolean setFlag()
    {
        if (flag)
        {
            super.setText("");
            flag = false;
        }
        else
        {
            super.setText("F");
            flag = true;
        }
        return flag;
    }

    // If flag is not a mine and flagged then incorrectly flagged (is true
    public boolean checkIncorrectlyFlagged()
    {
        return (!getMine() && flag == true);
    }
}
