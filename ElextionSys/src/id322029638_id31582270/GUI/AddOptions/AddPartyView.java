package id322029638_id31582270.GUI.AddOptions;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.BackButton;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.logic.WING;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddPartyView {
	VBox addViewPane;
	ArrayList<ElectionUIListenable> listners;
	
	public AddPartyView(ArrayList<ElectionUIListenable> listners,StackPane bgPane) {
		this.listners = listners;

		addViewPane = new VBox();

		Label lblTitle = new Label("Add Party");
		lblTitle.setFont(new Font("Curve", 35));
		addViewPane.getChildren().add(lblTitle);

		Label lblName = new Label("Name:");
		TextField tfNmae = new TextField();

		RadioButton rbBoxType1 = new RadioButton("Left");
		rbBoxType1.setUserData(WING.left);
		RadioButton rbBoxType2 = new RadioButton("Center");
		rbBoxType2.setUserData(WING.center);
		RadioButton rbBoxType3 = new RadioButton("Right");
		rbBoxType3.setUserData(WING.right);
		ToggleGroup tgType = new ToggleGroup();
		tgType.getToggles().addAll(rbBoxType1,rbBoxType2,rbBoxType3);
		tgType.selectToggle(null);

		Button btnAdd = new Button("ADD");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListenable electionUIListenable : listners) {
					electionUIListenable.addPartyIsPressed(tfNmae.getText(),WING.valueOf(tgType.getSelectedToggle().getUserData().toString()));					
				}
				tfNmae.clear();
				tgType.selectToggle(null);
			}
		});
		
		BackButton btnBack = new BackButton(bgPane,listners);

		GridPane gpBox = new GridPane();
		gpBox.add(lblName, 0, 0); 
		gpBox.add(tfNmae, 1, 0,2,1);
		
		gpBox.add(rbBoxType1, 0, 1);
		gpBox.add(rbBoxType2, 1, 1);
		gpBox.add(rbBoxType3, 2, 1);
		

		gpBox.add(btnAdd, 3, 2);
		gpBox.add(btnBack, 0, 2);

		gpBox.setVgap(20);
		gpBox.setHgap(10);
		gpBox.setAlignment(Pos.CENTER);
		
		addViewPane.getChildren().add(gpBox);
		addViewPane.setAlignment(Pos.TOP_CENTER);
	}

	public VBox getPane() {
		return this.addViewPane;
	}

}

