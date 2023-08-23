package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.text.*;



public class Main extends Application {

    private static Stage primaryStage;

    public static void setStartScene() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(50,50,50,50));
        gp.setVgap(10);
        gp.setHgap(5);
        Text welcome = new Text("Welcome");
        gp.add(welcome,0,0);

        Label lbRows = new Label("Rows:");
        TextField tfRows = new TextField();
        gp.add(lbRows,0,1);
        gp.add(tfRows,1,1);

        Label lbColumns = new Label("Columns:");
        TextField tfColumns = new TextField();
        gp.add(lbColumns,0,2);
        gp.add(tfColumns,1,2);

        Button btnGo = new Button("Go!");
        gp.add(btnGo,0,3,2,1);

        btnGo.setOnAction(value -> {
            primaryStage.setScene(setUpMineSweeper(Integer.parseInt(tfRows.getText()),Integer.parseInt(tfColumns.getText())));
        });
        primaryStage.setScene(new Scene(gp, 300, 275));
    }

    private static Scene setUpMineSweeper (int x, int y)
    {
        //int mines = 5;
        Board gpBoard = new Board(x,y);

        return new Scene(gpBoard,50+(y*35),50+(x*35));
    }

    @Override
    public void start(Stage ps) throws Exception{


        primaryStage = ps;
        primaryStage.setTitle("Minesweeper");
        GridPane gp = new GridPane();

        // Set Start Screen
        setStartScene();

        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
