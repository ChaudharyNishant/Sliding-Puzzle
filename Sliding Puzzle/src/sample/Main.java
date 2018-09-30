//Created by Nishant Chaudhary
//https://github.com/ChaudharyNishant

package sample;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class Main extends Application
{
	static int lvl = 1;
	static boolean win = false;
	Label moves = new Label("Moves: 0");
	Label level = new Label();
	int nMoves = 1;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		VBox root = new VBox();
		root.setId("root");
		HBox top = new HBox();
		Button newGame = new Button("New Game");
		newGame.setPrefHeight(30);
		newGame.setPrefWidth(100);//80
		newGame.setId("red");
		Button restartLevel = new Button("Restart Level");
		restartLevel.setPrefHeight(30);
		restartLevel.setPrefWidth(106);//85
		restartLevel.setId("yellow");
		Button quit = new Button("Quit");
		quit.setPrefHeight(30);
		quit.setPrefWidth(53);//50
		quit.setId("red");
		HBox stats = new HBox();
		moves.setId("label");
		level.setId("label");
		GridPane grid = new GridPane();
		Button arr[][] = new Button[3][3];
		
		int i, j;
		for(i = 0; i < 3; i++)
			for(j = 0; j < 3; j++)
			{
				arr[i][j] = new Button();
				grid.add(arr[i][j], j, i);
				arr[i][j].setPrefHeight(60);
				arr[i][j].setPrefWidth(60);
			}
		
		GeneratePuzzle(arr);
		
		for(i = 0; i < 3; i++)
			for(j = 0; j < 3; j++)
			{
				int x = i, y = j;
				arr[i][j].setOnAction(e -> Slide(arr, x, y));
			}
		
		newGame.setOnAction(e -> 
		{
			if(!win)
			{
				lvl = 1;
				GeneratePuzzle(arr);
			}
		});
		restartLevel.setOnAction(e ->
		{
			if(!win)
				GeneratePuzzle(arr);
		});
		quit.setOnAction(e -> System.exit(0));
		
		HBox.setMargin(restartLevel, new Insets(0, 0, 0, 5));
		HBox.setMargin(quit, new Insets(0, 0, 0, 5));
		HBox.setMargin(moves, new Insets(0, 0, 0, 15));
		HBox.setMargin(level, new Insets(0, 0, 0, 143));
		top.setPadding(new Insets(10, 0, 5, 11));
		VBox.setMargin(grid, new Insets(5, 0, 0, 50));
		
		top.getChildren().addAll(newGame, restartLevel, quit);
		stats.getChildren().addAll(moves, level);
		root.getChildren().addAll(top, stats, grid);
        Scene scene = new Scene(root, 280, 265);//235, 260
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Sliding Puzzle");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	void GeneratePuzzle(Button arr[][])
	{
		win = false;
		moves.setText("Moves: 0");
		nMoves = 1;
		level.setText("Level: " + Integer.toString(lvl));
		
		Random rand = new Random();
		int a = 2, b = 2, ch, k = 1;
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++, k++)
			{
				arr[i][j].setText(Integer.toString(k));
				arr[i][j].setId("coral");
			}
		for(k = 0; k < (2 * lvl) - 1; k++)
		{
			if(a==0 && b==0)
			{
				ch = rand.nextInt(2);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a][b+1].getText());
							arr[a][b+1].setText("");
							b++;
							break;
					case 1:	arr[a][b].setText(arr[a+1][b].getText());
							arr[a+1][b].setText("");
							a++;
							break;
				}
			}
			else if(a==0 && b==2)
			{
				ch = rand.nextInt(2);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a][b-1].getText());
							arr[a][b-1].setText("");
							b--;
							break;
					case 1:	arr[a][b].setText(arr[a+1][b].getText());
							arr[a+1][b].setText("");
							a++;
							break;
				}
			}
			else if(a==2 && b==2)
			{
				ch = rand.nextInt(2);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a][b-1].getText());
							arr[a][b-1].setText("");
							b--;
							break;
					case 1:	arr[a][b].setText(arr[a-1][b].getText());
							arr[a-1][b].setText("");
							a--;
							break;
				}
			}
			else if(a==2 && b==0)
			{
				ch = rand.nextInt(2);
				switch(ch)
				{
					
					case 0:	arr[a][b].setText(arr[a-1][b].getText());
							arr[a-1][b].setText("");
							a--;
							break;
					case 1:	arr[a][b].setText(arr[a][b+1].getText());
							arr[a][b+1].setText("");
							b++;
							break;
				}
			}
			else if(a==1 && b==0)
			{
				ch = rand.nextInt(3);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a-1][b].getText());
							arr[a-1][b].setText("");
							a--;
							break;
					case 1:	arr[a][b].setText(arr[a][b+1].getText());
							arr[a][b+1].setText("");
							b++;
							break;
					case 2:	arr[a][b].setText(arr[a+1][b].getText());
							arr[a+1][b].setText("");
							a++;
							break;
				}
			}
			else if(a==0 && b==1)
			{
				ch = rand.nextInt(3);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a][b-1].getText());
							arr[a][b-1].setText("");
							b--;
							break;
					case 1:	arr[a][b].setText(arr[a][b+1].getText());
							arr[a][b+1].setText("");
							b++;
							break;
					case 2:	arr[a][b].setText(arr[a+1][b].getText());
							arr[a+1][b].setText("");
							a++;
							break;
				}
			}
			else if(a==1 && b==2)
			{
				ch = rand.nextInt(3);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a][b-1].getText());
							arr[a][b-1].setText("");
							b--;
							break;
					case 1:	arr[a][b].setText(arr[a-1][b].getText());
							arr[a-1][b].setText("");
							a--;
							break;
					case 2:	arr[a][b].setText(arr[a+1][b].getText());
							arr[a+1][b].setText("");
							a++;
							break;
				}
			}
			else if(a==2 && b==1)
			{
				ch = rand.nextInt(3);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a][b-1].getText());
							arr[a][b-1].setText("");
							b--;
							break;
					case 1:	arr[a][b].setText(arr[a-1][b].getText());
							arr[a-1][b].setText("");
							a--;
							break;
					case 2:	arr[a][b].setText(arr[a][b+1].getText());
							arr[a][b+1].setText("");
							b++;
							break;
				}
			}
			else
			{
				ch = rand.nextInt(4);
				switch(ch)
				{
					case 0:	arr[a][b].setText(arr[a][b-1].getText());
							arr[a][b-1].setText("");
							b--;
							break;
					case 1:	arr[a][b].setText(arr[a-1][b].getText());
							arr[a-1][b].setText("");
							a--;
							break;
					case 2:	arr[a][b].setText(arr[a][b+1].getText());
							arr[a][b+1].setText("");
							b++;
							break;
					case 3:	arr[a][b].setText(arr[a+1][b].getText());
							arr[a+1][b].setText("");
							a++;
							break;
				}
			}
		}
		arr[a][b].setId("khaki");
	}
	
	void Slide(Button arr[][], int x, int y)
	{
		if(!win)
		{
			if(x > 0)
			{
				if(arr[x-1][y].getText().equals(""))
				{
					arr[x-1][y].setText(arr[x][y].getText());
					arr[x-1][y].setId("coral");
					arr[x][y].setText("");
					arr[x][y].setId("khaki");
					Check(arr);
				}
			}
			if(x < 2)
			{
				if(arr[x+1][y].getText().equals(""))
				{
					arr[x+1][y].setText(arr[x][y].getText());
					arr[x+1][y].setId("coral");
					arr[x][y].setText("");
					arr[x][y].setId("khaki");
					Check(arr);
				}
			}
			if(y > 0)
			{
				if(arr[x][y-1].getText().equals(""))
				{
					arr[x][y-1].setText(arr[x][y].getText());
					arr[x][y-1].setId("coral");
					arr[x][y].setText("");
					arr[x][y].setId("khaki");
					Check(arr);
				}
			}
			if(y < 2)
			{
				if(arr[x][y+1].getText().equals(""))
				{
					arr[x][y+1].setText(arr[x][y].getText());
					arr[x][y+1].setId("coral");
					arr[x][y].setText("");
					arr[x][y].setId("khaki");
					Check(arr);
				}
			}
		}
	}
	
	void Check(Button arr[][])
	{
		moves.setText("Moves: " + Integer.toString(nMoves++));
		
		int i, j, k = 1;
		boolean flag = true;
		
		for(i = 0; i < 3; i++)
			for(j = 0; j < 3; j++, k++)
			{
				if(k == 9)
				{
					if(!arr[i][j].getText().equals(""))
						flag = false;
				}
				else
				{
					if(!arr[i][j].getText().equals(Integer.toString(k)))
						flag = false;
				}
			}
		if(flag)
			Win(arr);
	}
	
	void Win(Button arr[][])
	{
		win = true;
		
		Stage finishStage = new Stage();
        VBox finishVBox = new VBox();
        finishVBox.setId("root");
        Label finish = new Label("Successful");
        finish.setId("label");
        Button nextLevel = new Button("Next Level");
        nextLevel.setId("yellow");
        Button quit = new Button("Quit");
        quit.setId("red");
        
        nextLevel.setOnAction(e2 ->
        {
            finishStage.close();
            lvl++;
            GeneratePuzzle(arr);
        });
        
        quit.setOnAction(e -> System.exit(0));
        
        finishStage.setOnCloseRequest(e -> Win(arr));
        
        finishVBox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(finish, new Insets(10, 0, 0, 0));
        VBox.setMargin(nextLevel, new Insets(10, 0, 0, 0));
        VBox.setMargin(quit, new Insets(5, 0, 0, 0));
        
        finishVBox.getChildren().addAll(finish, nextLevel, quit);
        Scene finishScene = new Scene(finishVBox, 200, 110);
        finishScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        finishStage.setTitle("Level Completed");
        finishStage.setResizable(false);
        finishStage.setAlwaysOnTop(true);
        finishStage.setScene(finishScene);
        finishStage.show();
	}
	
	public static void main(String args[])
	{
		launch(args);
	}	
}

//Created by Nishant Chaudhary
//https://github.com/ChaudharyNishant
