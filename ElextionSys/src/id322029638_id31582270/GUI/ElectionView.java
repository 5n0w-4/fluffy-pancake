package id322029638_id31582270.GUI;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ElectionView extends Application implements ElectionAbstractView {
	ArrayList<Button> menu = new ArrayList<>();
	
	public Button addOptionBtn(String optionName) {
		Button btn = new Button(optionName);
		btn.getStylesheets().add("file:///C:/css/custBtn.css");
		btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox.setHgrow(btn, Priority.ALWAYS);
		VBox.setVgrow(btn, Priority.ALWAYS);
		return btn;
	}
	
	public StackPane buildAddOption(String type) {
		StackPane addViewPane = new StackPane();
		Label lblName = new Label();
		TextField tfNmae = new TextField();
		
		Label id = new Label();
		TextField tfId = new TextField();
		
		GridPane gpBox = new GridPane();
		gpBox.add(lblName, 0, 0);
		gpBox.add(tfNmae, 0, 1);
		
		gpBox.add(id, 1, 0);
		gpBox.add(tfId, 1, 1);
		
		return addViewPane;
	}


	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Elections");
//		BorderPane bgPane = new BorderPane();
		StackPane bgPane = new StackPane();
		bgPane.setBackground(
				new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

		VBox vbMenu = new VBox();
		vbMenu.setSpacing(15);
		vbMenu.setAlignment(Pos.CENTER);
		vbMenu.setPrefWidth(100);

		menu.add(this.addOptionBtn("Add Ballot Box"));
		menu.add(this.addOptionBtn("Add Citizen"));
		menu.add(this.addOptionBtn("Add Party"));
		menu.add(this.addOptionBtn("Add Party Representative"));
		menu.add(this.addOptionBtn("Show Ballot Boxes"));
		menu.add(this.addOptionBtn("Show Citizens"));
		menu.add(this.addOptionBtn("Show Partys"));
		menu.add(this.addOptionBtn("Start Voting"));
		menu.add(this.addOptionBtn("Show Results"));
		menu.add(this.addOptionBtn("Quit"));
		
//		for (Button button : menu) {
//			vbMenu.setVgrow(button,Priority.ALWAYS);
//		}

		vbMenu.getChildren().addAll(menu);
//		vbMenu.setMaxWidth(200);
		bgPane.getChildren().add(vbMenu);
		bgPane.setMinSize(150, 150);
		bgPane.setMargin(vbMenu, new Insets(50, 50, 50, 50));
//		bgPane.getChildren().add(vbMenu);
//		bgPane.setCenter(vbMenu);
		

		arg0.setMinHeight(500);
		arg0.setMinWidth(400);
		arg0.setScene(new Scene(bgPane, 350, 400));
		arg0.show();

	}

	public static void main(String args[]) {
		launch(args);
	}

}
