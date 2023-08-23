package sample;

import javafx.scene.control.Button;
import javafx.geometry.*;
import javafx.scene.control.Alert;

public class Square extends Button {
    public int posx;
    public int posy;
    private boolean isMine;
    public int surroundingMines;
    public boolean checked;



    public Square(int x, int y)
    {
        //Square.super.setText(Integer.toString(x)+" "+Integer.toString(y));
        posx = x;
        posy = y;
        isMine = false;
        checked = false;
        Square.super.setMinWidth(35);
        Square.super.setAlignment(Pos.CENTER);
        surroundingMines = 0;
    }


    public boolean checkSquare(){
        checked = true;
        super.setDisabled(true);
        if(isMine==true)
        {
            super.setText("*");
            Alert a = new Alert(Alert.AlertType.WARNING,"Sorry! You hit a mine!");
            a.showAndWait();
        }
        else
        {
            if (surroundingMines > 0) { super.setText(Integer.toString(surroundingMines));}
            else { super.setText("");}
        }
        return isMine;
    }

    public void setMine(){
        isMine = true;
    }
    public boolean getMine(){
        return isMine;
    }
}
