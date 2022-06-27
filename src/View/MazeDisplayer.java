package View;

import algorithms.mazeGenerators.Maze;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MazeDisplayer extends Canvas {
    private int[][] maze = null;
    // player position:
    private int playerRow;
    private int playerCol;
    private int goalRow;
    private int goalCol;

    // wall and player images:
    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty();
    StringProperty imageFileNameSolution = new SimpleStringProperty();
    StringProperty imageFileNameGoal = new SimpleStringProperty();
    StringProperty imageFileNameBack = new SimpleStringProperty();

    Image wallImage = null;
    Image solutionImage = null;
    Image backImage = null;
    Image playerImage = null;
    Image goalImage = null;



    public void initializeImages() {
        try {
            wallImage = new Image(new FileInputStream(getImageFileNameWall()));
            solutionImage = new Image(new FileInputStream(getImageFileNameSolution()));
            backImage = new Image(new FileInputStream(getImageFileNameBack()));
            playerImage = new Image(new FileInputStream(getImageFileNamePlayer()));
            goalImage = new Image(new FileInputStream(getImageFileNameGoal()));
        }
        catch (FileNotFoundException e) {
        }
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public void setPlayerPosition(int row, int col) {
        this.playerRow = row;
        this.playerCol = col;
        draw();
    }

    public String getImageFileNameWall() {
        return imageFileNameWall.get();
    }

    public String getImageFileNameSolution() {
        return imageFileNameSolution.get();
    }

    public String getImageFileNameGoal() {
        return imageFileNameGoal.get();
    }

    public String getImageFileNameBack() {
        return imageFileNameBack.get();
    }

    public String imageFileNameWallProperty() {
        return imageFileNameWall.get();
    }

    public String imageFileNameBackProperty() {
        return imageFileNameBack.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.imageFileNameWall.set(imageFileNameWall);
    }

    public void setImageFileNameBack(String imageFileNameBack) {
        this.imageFileNameBack.set(imageFileNameBack);
    }

    public String getImageFileNamePlayer() {
        return imageFileNamePlayer.get();
    }

    public String imageFileNamePlayerProperty() {
        return imageFileNamePlayer.get();
    }

    public void setImageFileNamePlayer(String imageFileNamePlayer) {
        this.imageFileNamePlayer.set(imageFileNamePlayer);
    }

    public String imageFileNameSolutionProperty() {
        return imageFileNameSolution.get();
    }

    public void setImageFileNameSolution(String imageFileNameSolution) {
        this.imageFileNameSolution.set(imageFileNameSolution);
    }

    public String imageFileNameGoalProperty() {
        return imageFileNameGoal.get();
    }

    public void setImageFileNameGoal(String imageFileNameGoal) {
        this.imageFileNameGoal.set(imageFileNameGoal);
    }


    public void setMaze(int[][] mazeArray, int startRow, int startCol, int goalRow, int goalCol) {
        this.goalRow = goalRow;
        this.goalCol = goalCol;
        refreshMaze(mazeArray, startRow, startCol);
    }

    public void refreshMaze(int[][] mazeArray, int playerRow, int playerCol)
    {
        this.maze = mazeArray;
        setPlayerPosition(playerRow, playerCol);
    }

    public void draw() {
        if(maze != null){
            double canvasHeight = getHeight(), canvasWidth = getWidth();
            int rows = maze.length, cols = maze[0].length;

            double cellHeight = canvasHeight / rows;
            double cellWidth = canvasWidth / cols;

            GraphicsContext graphicsContext = getGraphicsContext2D();
            //clear the canvas:
            graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);

            drawMazeWalls(graphicsContext, cellHeight, cellWidth, rows, cols);
            drawPlayer(graphicsContext, cellHeight, cellWidth);
            drawGoal(graphicsContext, cellHeight, cellWidth);
        }
    }

    private void drawMazeWalls(GraphicsContext graphicsContext, double cellHeight, double cellWidth, int rows, int cols) {
        graphicsContext.setFill(Color.RED);
        double x, y;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                x = j * cellWidth;
                y = i * cellHeight;
                if (maze[i][j] == 0){
                    //if it is a not a wall:
                    if(backImage == null)
                        graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                    else
                        graphicsContext.drawImage(backImage, x, y, cellWidth, cellHeight);
                }
                else if(maze[i][j] == 1){
                    //if it is a wall:
                    if(wallImage == null)
                        graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                    else
                        graphicsContext.drawImage(wallImage, x, y, cellWidth, cellHeight);
                }
                else if(maze[i][j] == 2)
                {
                    if(solutionImage == null)
                        graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                    else
                        graphicsContext.drawImage(solutionImage, x, y, cellWidth, cellHeight);
                }
            }
        }
    }

    private void drawPlayer(GraphicsContext graphicsContext, double cellHeight, double cellWidth) {
        double x = playerCol * cellWidth;
        double y = playerRow * cellHeight;
        graphicsContext.setFill(Color.GREEN);

        if(playerImage == null)
            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
        else
            graphicsContext.drawImage(playerImage, x, y, cellWidth, cellHeight);
    }

    private void drawGoal(GraphicsContext graphicsContext, double cellHeight, double cellWidth) {
        double x = goalCol * cellWidth;
        double y = goalRow * cellHeight;
        graphicsContext.setFill(Color.GREEN);

        if(goalImage == null)
            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
        else
            graphicsContext.drawImage(goalImage, x, y, cellWidth, cellHeight);
    }

    public boolean isResizable (){
        return true;
    }

    public void resize (double width, double height){
        super.setHeight(height - 20);
        super.setWidth(width - 100);
        draw();
    }
}
