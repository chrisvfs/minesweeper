package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.text.*;



public class Main extends Application {

    private static Stage primaryStage;

    // Function creates and sets intial scene with game parameters
    public static void setStartScene() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(50,50,50,50));
        gp.setVgap(10);
        gp.setHgap(5);
        Text welcome = new Text("Welcome");
        gp.add(welcome,0,0);

        // Rows
        Label lbRows = new Label("Rows:");
        TextField tfRows = new TextField();
        gp.add(lbRows,0,1);
        gp.add(tfRows,1,1);

        // Columns
        Label lbColumns = new Label("Columns:");
        TextField tfColumns = new TextField();
        gp.add(lbColumns,0,2);
        gp.add(tfColumns,1,2);

        // Mines
        Label lblMines = new Label("Mines:");
        TextField tfMines = new TextField();
        gp.add(lblMines,0,3);
        gp.add(tfMines,1,3);

        // Go button
        Button btnGo = new Button("Go!");
        gp.add(btnGo,0,4,2,1);

        btnGo.setOnAction(value -> {
            primaryStage.setScene(setUpMineSweeper(Integer.parseInt(tfRows.getText()),Integer.parseInt(tfColumns.getText()),Integer.parseInt(tfMines.getText())));
        });

        // Small difficulty preset
        Button btnSmall = new Button("Small");
        btnSmall.setOnAction(value -> {
            primaryStage.setScene(setUpMineSweeper(9,9,10));
        });

        // Medium difficulty preset
        Button btnMedium = new Button("Medium");
        btnMedium.setOnAction(value -> {
            primaryStage.setScene(setUpMineSweeper(13,15,40));
        });

        // Large difficulty preset
        Button btnLarge = new Button("Large");
        btnLarge.setOnAction(value -> {
            primaryStage.setScene(setUpMineSweeper(16,30,99));
        });

        HBox hbPresets = new HBox(8);
        hbPresets.getChildren().addAll(btnSmall,btnMedium,btnLarge);
        gp.add(hbPresets,0,5,2,1);
        // Set and run primary scene
        primaryStage.setScene(new Scene(gp, 300, 275));
    }

    private static Scene setUpMineSweeper (int x, int y, int m)
    {
        Board gpBoard = new Board(x,y,m);

        return new Scene(gpBoard,(y*25),(x*25));
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
