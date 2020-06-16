package id322029638_id31582270;

import id322029638_id31582270.GUI.ElectionView;
import id322029638_id31582270.logic.ElectionController;
import id322029638_id31582270.logic.Elections;
import javafx.application.Application;
import javafx.stage.Stage;


public class Program extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Elections theModel = new Elections();
		ElectionView theView1 = new ElectionView(primaryStage);
		ElectionController controller1 = new ElectionController(theModel, theView1);		
	}

}
