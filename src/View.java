import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
public class View extends Application
{
		GridPane grid = new GridPane();
		Button[][] button2d = new Button[18][18];
		Boolean lose = false;
		Boolean win = false;
	@Override
	public void start(Stage stage)
	{
		Model model = new Model(grid, button2d);
		model.createModel();
		grid.setLayoutX(90);
		grid.setLayoutY(110);
		grid.setAlignment(Pos.CENTER);
		grid.setVisible(false);
		
		Text bc = new Text("");
		bc.setLayoutX(400);
		bc.setLayoutY(75);
		
		Button play = new Button("Play");
		play.setLayoutX(50);
		play.setLayoutY(50);
		
		Button playagain = new Button("Play Again");
		playagain.setTranslateY(50);
		playagain.setTranslateX(150);
		playagain.setVisible(false);
		
		ImageView frown = new ImageView(new Image("file:frown.png"));
		frown.setFitHeight(40);
		frown.setFitWidth(40);
		frown.setX(307);
		frown.setY(50);
		frown.setVisible(false);
		
		ImageView happy = new ImageView(new Image("file:happy.png"));
		happy.setFitHeight(40);
		happy.setFitWidth(40);
		happy.setX(307);
		happy.setY(50);
		happy.setVisible(false);
		
		ImageView sunglasses = new ImageView(new Image("file:sunglasses.png"));
		sunglasses.setFitHeight(40);
		sunglasses.setFitWidth(40);
		sunglasses.setX(307);
		sunglasses.setY(50);
		sunglasses.setVisible(false);
		
		Controller controller = new Controller(grid, button2d, bc, playagain, frown, happy, sunglasses);
		controller.clickGrid();
		
		play.setOnAction(event -> 
		{
			grid.setVisible(true);
			play.setDisable(true);
		});
				
		playagain.setOnAction(event ->
		{
			controller.reset();
			play.setDisable(false);
			playagain.setVisible(false);
			grid.setDisable(false);
			grid.setVisible(false);
		});
		
		Group root = new Group(play,playagain,grid,frown,happy,sunglasses,bc);
        Scene scene = new Scene(root, 650, 650);
        stage.setTitle("Mine Sweeper");
        stage.setScene(scene);
        stage.show();
	}
	public static void main(String args[])
	{
		launch(args);
	}
}

