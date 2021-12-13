package sierpinskitri;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;



public class STriangle extends Application {
    // Override the start method in the Application class
    @Override
    public void start(Stage primaryStage) throws Exception {
        SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();
        TextField tfOrder = new TextField();
        tfOrder.setOnAction(
                e  -> trianglePane.setOrder(Integer.parseInt(tfOrder.getText())));
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT);
        // Pane to hold label, text field, and a button
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter a number: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(trianglePane);
        borderPane.setBottom(hBox);
        // Create a scene and place it in the stag
        Scene scene = new Scene(borderPane, 500, 510);
        primaryStage.setTitle("SierpinskiTriangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static class SierpinskiTrianglePane extends Pane {
        private int depth = 0;

        SierpinskiTrianglePane() {
        }

        public int getOrder() {
            return depth;
        }
        /** Set a new order */
        public void setOrder(int depth) {
            this.depth = depth;
            drawTriangles();
        }

        private void drawTriangles() {
            // Select three points in proportion to the pane size
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            getChildren().clear();// Clear the pane before redisplay
            sierpinski(depth, p1, p2, p3);
        }

        private void sierpinski(int depth, Point2D p1, Point2D p2, Point2D p3) {
            if (depth == 0) {
                // Draw a triangle to connect three points
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
                triangle.setFill(Color.PINK);
                triangle.setStroke(Color.BLACK);
                this.getChildren().add(triangle);
            } else {
                // Get the midpoint on each edge in the triangle
                Point2D mid12 = p1.midpoint(p2);
                Point2D mid23 = p2.midpoint(p3);
                Point2D mid31 = p3.midpoint(p1);
                // Recursively display three triangles
                sierpinski(depth - 1, p1, mid12, mid31);
                sierpinski(depth - 1, mid12, p2, mid23);
                sierpinski(depth - 1, mid31, mid23, p3);
            }
        }
    }
}