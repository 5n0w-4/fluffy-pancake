package id322029638_id31582270.GUI.AddOptions;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.BackButton;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.population.Voter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddCitizenView {
	VBox addViewPane;
	ArrayList<ElectionUIListenable> listners;
	public AddCitizenView(ArrayList<ElectionUIListenable> listners,StackPane bgPane) {
		
		this.listners = listners;
		addViewPane = new VBox();
		
		Label lblTitle = new Label("Add Citizen");
		lblTitle.setFont(new Font("Curve", 35));
		addViewPane.getChildren().add(lblTitle);

		Label lblName = new Label("Name:");
		TextField tfNmae = new TextField();

		Label id = new Label("ID:");
		TextField tfId = new TextField();

		Label birthYear = new Label("Birth year:");
		TextField tfBirthYear = new TextField();

//		ComboBox<Integer> daysInfected;
//		daysInfected = new ComboBox<Integer>();
//		for (int i = 0; i < 150; i++) {
//			daysInfected.getItems().add(i);
//		}
//		daysInfected.setVisible(false);
		

		CheckBox cbInfected = new CheckBox("Infected");
//		cbInfected.selectedProperty().addListener(new ChangeListener<Boolean>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				// TODO Auto-generated method stub
//				if (newValue) {
//					daysInfected.setVisible(true);
//				} else {
//					daysInfected.setVisible(false);
//				}
//			}
//
//		});


		Button btnAdd = new Button("ADD");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListenable electionUIListenable : listners) {
					electionUIListenable.addCitizenIsPressed(tfNmae.getText(), tfId.getText(), tfBirthYear.getText(),
							cbInfected.isSelected());
				}
				tfNmae.clear();
				tfId.clear();
				tfBirthYear.clear();
				cbInfected.setSelected(false);
			}
		});

		BackButton btnBack = new BackButton(bgPane,listners);

		GridPane gpBox = new GridPane();
		gpBox.add(lblName, 0, 0);
		gpBox.add(tfNmae, 1, 0);

		gpBox.add(id, 0, 1);
		gpBox.add(tfId, 1, 1);

		gpBox.add(birthYear, 0, 2);
		gpBox.add(tfBirthYear, 1, 2);

		gpBox.add(cbInfected, 3, 0);
//		gpBox.add(daysInfected, 3, 1);

		gpBox.add(btnAdd, 3, 3);
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
