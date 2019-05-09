import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import java.util.Random;
public class Controller 
{
	GridPane grid = new GridPane();
	Button[][] button2d = new Button[18][18];
	Button playagain;
	public int[][] grid2 = new int[18][18];
	public int[][] grid3 = new int [18][18];
	Text bctext = new Text("");
	Boolean fclick = true;
	public int rbc = 40, bc = 40;
	int r, rr;
	ImageView frown, happy, sunglasses;
	
	public Controller(GridPane grid, Button[][] button2d, Text bc, Button playagain, ImageView frown, ImageView happy, ImageView sunglasses)
	{
		this.grid = grid;
		this.button2d = button2d;
		this.playagain = playagain;
		this.bctext = bc;
		this.frown = frown;
		this.happy = happy;
		this.sunglasses = sunglasses;
	}
	
	//1 = bomb, 2 = flag, 3 = clicked, 4 = not clicked
	public void clickGrid() 
	{
		for(int x = 1; x < 17; x++) 
		{
			for(int y = 1; y < 17; y++)
			{
				grid2[x][y] = 4;
				grid3[x][y] = 10;
				final int xx = x;
				final int yy = y;
				button2d[xx][yy].setOnMouseClicked(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event) 
					{
						MouseButton button = event.getButton();
						if(button == MouseButton.PRIMARY)
						{
							if(grid2[xx][yy] != 2 && grid2[xx][yy] != 3)
							{
								if(fclick == true)
									placeBombs(xx,yy);
								fclick = false;	
								checkBlocks(xx,yy);
								if(grid3[xx][yy] == 0)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:empty.png")));
									happy.setVisible(true);
									grid2[xx][yy] = 3;
									checkEmpty(xx,yy);
								}
								else if(grid3[xx][yy] == 1)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:1.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 2)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:2.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 3)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:3.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 4)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:4.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 5)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:5.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 6)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:6.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 7)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:7.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 8)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:8.png")));
									happy.setVisible(true);
								}
								else if(grid3[xx][yy] == 9)
								{
									button2d[xx][yy].setGraphic(new ImageView(new Image("file:bomb.png")));
									playagain.setVisible(true);
									happy.setVisible(false);
									sunglasses.setVisible(false);
									frown.setVisible(true);
									for(int a = 1; a < 17; a++)
									{
										for(int b = 1; b < 17; b++)
										{
											if(checkBlocks(a, b) == 9)
											{
												button2d[a][b].setGraphic(new ImageView(new Image("file:bomb.png")));
											}
										}
									}
									grid.setDisable(true);
								}
							}
						}
						else if(button == MouseButton.SECONDARY)
						{
							if(checkBlocks(xx,yy) == 9)
							{
								rbc--;
							}
							if(grid2[xx][yy] != 2 && grid2[xx][yy] != 3)
							{
								button2d[xx][yy].setGraphic(new ImageView(new Image("file:flag.png")));
								grid2[xx][yy] = 2;
								bc--;
								bctext.setVisible(true);
								bctext.setText("Bombs Remaining: " + bc);
							}	
							else if(grid2[xx][yy] == 2)
							{
								button2d[xx][yy].setGraphic(new ImageView(new Image("file:unopened.png")));
								grid2[xx][yy] = 4;
								bc++;
								bctext.setVisible(true);
								bctext.setText("Bombs Remaining: " + bc);
								if(checkBlocks(xx,yy) == 9)
								{
									rbc++;
								}
							}
							if(rbc == 0)
							{
								sunglasses.setVisible(true);
								frown.setVisible(false);
								happy.setVisible(false);
								grid.setDisable(true);
								playagain.setVisible(true);
							}
						}
					}
				});
			}
		}			
	}
	
	public void placeBombs(int xx, int yy)
	{
		int low = 1;
		int high = 17;
		Random rand = new Random();
		for(int b = 0; b < 40; b++)
		{
			r = rand.nextInt(high - low) + low;
			rr = rand.nextInt(high - low) + low;
			if(grid2[r][rr] != 1)
			{
				grid2[r][rr] = 1;
			}
			else
			{
				b--;
			}
			if(r == xx && rr == yy)
			{
				grid2[xx][yy] = 4;		
				b--;
			}	
		}
	}
	
	public void checkEmpty(int x, int y)
	{
	    if(grid2[x-1][y] == 4 && checkBlocks(x-1,y) == 0)//left
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:empty.png")));
			checkEmpty(x-1,y);
	    }
	    else if(grid3[x-1][y] == 1)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x-1][y] == 2)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x-1][y] == 3)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x-1][y] == 4)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x-1][y] == 5)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x-1][y] == 6)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x-1][y] == 7)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x-1][y] == 8)
	    {
	    	grid2[x-1][y] = 3;
			button2d[x-1][y].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	    
	    if(grid2[x-1][y-1] == 4 && checkBlocks(x-1,y-1) == 0)//top left
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:empty.png")));
			checkEmpty(x-1,y-1);
	    }
	    else if(grid3[x-1][y-1] == 1)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x-1][y-1] == 2)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x-1][y-1] == 3)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x-1][y-1] == 4)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x-1][y-1] == 5)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x-1][y-1] == 6)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x-1][y-1] == 7)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x-1][y-1] == 8)
	    {
	    	grid2[x-1][y-1] = 3;
			button2d[x-1][y-1].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	    
	    if(grid2[x][y-1] == 4 && checkBlocks(x,y-1) == 0)//top
	    {
	    	grid2[x][y-1] = 3;
	    	button2d[x][y-1].setGraphic(new ImageView(new Image("file:empty.png")));
	    	checkEmpty(x,y-1);
	    }
	    else if(grid3[x][y-1] == 1)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x][y-1] == 2)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x][y-1] == 3)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x][y-1] == 4)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x][y-1] == 5)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x][y-1] == 6)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x][y-1] == 7)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x][y-1] == 8)
	    {
	    	grid2[x][y-1] = 3;
			button2d[x][y-1].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	    
	    if(grid2[x+1][y-1] == 4 && checkBlocks(x+1,y-1) == 0)//top right
	    {
	    	grid2[x+1][y-1] = 3;
	    	button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:empty.png")));
	    	checkEmpty(x+1,y-1);
	    }
	    else if(grid3[x+1][y-1] == 1)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x+1][y-1] == 2)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x+1][y-1] == 3)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x+1][y-1] == 4)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x+1][y-1] == 5)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x+1][y-1] == 6)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x+1][y-1] == 7)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x+1][y-1] == 8)
	    {
	    	grid2[x+1][y-1] = 3;
			button2d[x+1][y-1].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	    
	    if(grid2[x+1][y] == 4 && checkBlocks(x+1,y) == 0)//right
	    {
	    	grid2[x+1][y] = 3;
	    	button2d[x+1][y].setGraphic(new ImageView(new Image("file:empty.png")));
	    	checkEmpty(x+1,y);
	    }
	    else if(grid3[x+1][y] == 1)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x+1][y] == 2)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x+1][y] == 3)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x+1][y] == 4)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x+1][y] == 5)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x+1][y] == 6)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x+1][y] == 7)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x+1][y] == 8)
	    {
	    	grid2[x+1][y] = 3;
			button2d[x+1][y].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	    
	    if(grid2[x+1][y+1] == 4 && checkBlocks(x+1,y+1) == 0)//bottom right
	    {
	    	grid2[x+1][y+1] = 3;
	    	button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:empty.png")));
	    	checkEmpty(x+1,y+1);
	    }
	    else if(grid3[x+1][y+1] == 1)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x+1][y+1] == 2)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x+1][y+1] == 3)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x+1][y+1] == 4)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x+1][y+1] == 5)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x+1][y+1] == 6)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x+1][y+1] == 7)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x+1][y+1] == 8)
	    {
	    	grid2[x+1][y+1] = 3;
			button2d[x+1][y+1].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	    
	    if(grid2[x][y+1] == 4 && checkBlocks(x,y+1) == 0)//bottom
	    {
	    	grid2[x][y+1] = 3;
	    	button2d[x][y+1].setGraphic(new ImageView(new Image("file:empty.png")));
	    	checkEmpty(x,y+1);
	    }
	    else if(grid3[x][y+1] == 1)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x][y+1] == 2)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x][y+1] == 3)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x][y+1] == 4)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x][y+1] == 5)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x][y+1] == 6)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x][y+1] == 7)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x][y+1] == 8)
	    {
	    	grid2[x][y+1] = 3;
			button2d[x][y+1].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	    
	    if(grid2[x-1][y+1] == 4 && checkBlocks(x-1,y+1) == 0)//bottom left
	    {
	    	grid2[x-1][y+1] = 3;
	    	button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:empty.png")));
	    	checkEmpty(x-1,y+1);
	    }
	    else if(grid3[x-1][y+1] == 1)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:1.png")));
	    }
	    else if(grid3[x-1][y+1] == 2)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:2.png")));
	    }
	    else if(grid3[x-1][y+1] == 3)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:3.png")));
	    }
	    else if(grid3[x-1][y+1] == 4)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:4.png")));
	    }
	    else if(grid3[x-1][y+1] == 5)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:5.png")));
	    }
	    else if(grid3[x-1][y+1] == 6)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:6.png")));
	    }
	    else if(grid3[x-1][y+1] == 7)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:7.png")));
	    }
	    else if(grid3[x-1][y+1] == 8)
	    {
	    	grid2[x-1][y+1] = 3;
			button2d[x-1][y+1].setGraphic(new ImageView(new Image("file:8.png")));
	    }
	 }
	
	public int checkBlocks(int x, int y)
	{
		int count = 0;
	    if(grid2[x][y] == 1)
	    {
	    	grid3[x][y] = 9;
	        return 9;
	    }
	    
	    if (grid2[x-1][y] == 1)
	    {
	        count++;
	      //  System.out.println("bomb to your left");
	    }
	  
	    if (grid2[x-1][y-1] == 1)
	    {
	        count++;
	        //System.out.println("bomb to your top left");
	    }

	    if (grid2[x][y-1] == 1)
	    {
	        count++;
	       // System.out.println("bomb to your top");
	    }
	    
	    if (grid2[x+1][y-1] == 1)
	    {
	        count++;
	       // System.out.println("bomb to your top right");
	    }
	  
	    if (grid2[x+1][y] == 1)
	    {
	        count++;
	       // System.out.println("bomb to your right");
	    }
	   
	    if (grid2[x+1][y+1] == 1)
	    {
	        count++;
	      //  System.out.println("bomb to your bottom right");
	    }
	    
	    if (grid2[x][y+1] == 1)
	    {
	        count++;
	        //System.out.println("bomb to your bottom");
	    }
	    
	    if (grid2[x-1][y+1] == 1)
	    {
	        count++;
	      //  System.out.println("bomb to your bottom left");
	    }
	    grid3[x][y] = count;
	    	return count;
	 }
		
	public void reset()
	{
		fclick = true;
		sunglasses.setVisible(false);
		happy.setVisible(false);
		frown.setVisible(false);
		bctext.setVisible(false);
		bc = 40;
		rbc = 40;
		for(int x = 1; x < 17; x++)
		{
			for(int y = 1; y < 17; y++)
			{
				grid2[x][y] = 4;
				button2d[x][y].setGraphic(new ImageView(new Image("file:unopened.png")));
			}
		}
	}
	
	public static void main(String args[])
	{
		
	}
}
