package sierpinskitri;


import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;

import javafx.scene.shape.Line;

import javafx.stage.Stage;

public class CircleFractal extends Application {

    @Override

    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        TextField tfOrder = new TextField();
        tfOrder.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                drawFractalBr(pane, Integer.parseInt(tfOrder.getText()));
            }
        });
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter a number: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 600, 600);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Fractals");

        primaryStage.show();

    }


    //recursive method to draw fractals
    void drawFractalBr(Pane pane, int levels){
        pane.getChildren().clear();
        drawFractal(pane, 300, 300, 30,levels);
    }

    void drawFractal(Pane p, double x, double y, double radius, int levels) {

        //if levels go negative, we stop. this is the base condition

        if (levels < 0) {

            return;

        }

        //checking if levels > 0,

        if (levels > 0) {

            //we will divide a circle (360 degree) into connections number of points

            int connections = 3;

            //looping for connections number of times

            for (int i = 0; i < connections; i++) {

                //finding angle of rotation

                double angle = i * (360.0 / connections);

                //finding a coordinate which is 4 * radius distance away from center coordinates

                double xx = x + (4 * radius) * Math.cos(Math.toRadians(angle));

                double yy = y + (4 * radius) * Math.sin(Math.toRadians(angle));

//
                Line line = new Line(x, y, xx, yy);

                p.getChildren().add(line);

                drawFractal(p, xx, yy, radius / 2.0, levels - 1);

            }

        }

        //finally we create a circle with radius at x,y position, and add to the pane.

        //we are drawing the circle at the end, to prevent the lines from being drawn over the circles

        Circle oval = new Circle(x, y, radius);

        oval.setFill(Color.GRAY); //if just want outlines, then use this line!

        oval.setStroke(Color.BLACK);

        p.getChildren().addAll(oval); //magic line to add new oval to pane

    }//end drawCircle

    public static void main(String[] args) {

        launch(args);

    }
}