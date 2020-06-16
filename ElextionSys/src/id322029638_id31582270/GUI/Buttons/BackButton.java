package id322029638_id31582270.GUI.Buttons;

import java.util.ArrayList;

import id322029638_id31582270.GUI.CreateMenuView;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class BackButton extends Button {

	public BackButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BackButton(StackPane bgPane,ArrayList<ElectionUIListenable> listners) {
		super("Back");
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				bgPane.getChildren().clear();
				bgPane.getChildren().add(new CreateMenuView(listners,bgPane).getPane());	
				listners.get(0).changeWindowSize(260, 620);

			}
		});
	}
	
	
	
}
