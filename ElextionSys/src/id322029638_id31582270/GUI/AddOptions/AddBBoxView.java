package id322029638_id31582270.GUI.AddOptions;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.BackButton;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.population.CoronoaPatient;
import id322029638_id31582270.population.InfectedSolider;
import id322029638_id31582270.population.Solider;
import id322029638_id31582270.population.Voter;
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

public class AddBBoxView {
	VBox addViewPane;
	ArrayList<ElectionUIListenable> listners;
	
	public AddBBoxView(ArrayList<ElectionUIListenable> listners,StackPane bgPane) {
		this.listners = listners;

		addViewPane = new VBox();

		Label lblTitle = new Label("Add Ballot Box");
		lblTitle.setFont(new Font("Curve", 35));
		addViewPane.getChildren().add(lblTitle);

		Label lblAdress = new Label("Adress:");
		TextField tfAdress = new TextField();

		RadioButton rbBoxType1 = new RadioButton("Corona Ballot Box");
		rbBoxType1.setUserData(CoronoaPatient.class);
		RadioButton rbBoxType2 = new RadioButton("Regular Ballot Box");
		rbBoxType2.setUserData(Voter.class);
		RadioButton rbBoxType3 = new RadioButton("Army Ballot Box");
		rbBoxType3.setUserData(Solider.class);
		RadioButton rbBoxType4 = new RadioButton("Corona-Army Ballot Box");
		rbBoxType4.setUserData(InfectedSolider.class);

		ToggleGroup tgType = new ToggleGroup();
		tgType.getToggles().addAll(rbBoxType1,rbBoxType2,rbBoxType3,rbBoxType4);
		
	

		Button btnAdd = new Button("ADD");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListenable electionUIListenable : listners) {
					electionUIListenable.addBoxIsPressed(tfAdress.getText(),(Class<?>)tgType.getSelectedToggle().getUserData());
				}
				tfAdress.clear();
				tgType.selectToggle(null);

			}
		});
		
		BackButton btnBack = new BackButton(bgPane,listners);

		GridPane gpBox = new GridPane();
		
		gpBox.setVgap(10);
		gpBox.setHgap(10);
		
		gpBox.add(lblAdress, 0, 0);
		gpBox.add(tfAdress, 1, 0);
		
		gpBox.add(rbBoxType1, 0, 1);
		gpBox.add(rbBoxType2, 1, 1);
		gpBox.add(rbBoxType3, 0, 2);
		gpBox.add(rbBoxType4, 1, 2);
		

		gpBox.add(btnAdd, 2, 3);
		gpBox.add(btnBack, 0, 3);

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
