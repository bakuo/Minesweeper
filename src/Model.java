import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
public class Model
{
	GridPane grid = new GridPane();
	Button[][] button2d = new Button[18][18];

	public Model(GridPane gridPane, Button[][] button2d)
	{
		this.grid = gridPane;
		this.button2d = button2d;
	}
	
	public void createModel()
	{
		for(int x = 1; x < 17; x++) 
		{
			for(int y = 1; y < 17; y++)
			{
				button2d[x][y] = new Button("");
				button2d[x][y].setMinSize(30, 30);
				button2d[x][y].setMaxSize(30, 30);
				button2d[x][y].setGraphic(new ImageView(new Image("file:unopened.png")));
				grid.add(button2d[x][y], x, y);
			}
		}
	}
}

