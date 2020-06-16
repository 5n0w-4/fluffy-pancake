package id322029638_id31582270.GUI;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.BackButton;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.logic.Party;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartElectionsView {
	VBox addViewPane;
	ArrayList<ElectionUIListenable> listners;
	private int counter;
	private ArrayList<Voter> voters;
	Voter voter;
	Button btnVote;

	private Voter nextVoter() {
		voters = listners.get(0).viewAsksGetCitizen();
		if (counter == voters.size()) {
			counter = 0;
			return null;
		}
		voter = voters.get(counter);
		counter++;
		return voter;

	}
	private Party convertToParty(String name) {
		for (Party party : listners.get(0).viewAsksGetPartys()) {
			if (name.equals(party.getName())) {
				return party;
			}
		}
		return null;
	}

	public StartElectionsView(ArrayList<ElectionUIListenable> listners,StackPane bgPane) {
		
		this.listners = listners;
		addViewPane = new VBox();
		
		Label lblTitle = new Label("Start Elections");
		lblTitle.setFont(new Font("Curve", 35));
		addViewPane.getChildren().add(lblTitle);

		Label lblName = new Label("Name:");
		Label lblId = new Label("ID:");
		Label lblNameValue = new Label("Press Start");
		Label lblIdValue = new Label("Press Start");
		Label lblElectionEnded = new Label("Election ended");
		ComboBox<String> cbPartys = new ComboBox<String>();
		
		cbPartys.setVisible(false);
		lblElectionEnded.setVisible(false);
		
		for (ElectionUIListenable electionUIListenable : listners) {
			for (Party party : electionUIListenable.viewAsksGetPartys()) {
				cbPartys.getItems().add(party.getName());
			}
		}

		CheckBox cbIsVoting = new CheckBox("Voting?");
		cbIsVoting.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (newValue) {
					cbPartys.setVisible(true);
					btnVote.setText("Vote");
					
				} else {
					cbPartys.setVisible(false);
					btnVote.setText("Skip");

				}
			}

		});
		
		Button btnStart = new Button("Start");
		btnStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				btnVote.setVisible(true);
				btnStart.setVisible(false);
				nextVoter();
				lblNameValue.setText(voter.getName());
				lblIdValue.setText(voter.getId());
			}
		});


		btnVote = new Button("Skip");
		btnVote.setVisible(false);
		btnVote.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListenable electionUIListenable : listners) {
					if (!cbIsVoting.isSelected()) {
						nextVoter();
						lblNameValue.setText(voter.getName());
						lblIdValue.setText(voter.getId());
					}
					electionUIListenable.startVotingIsPressed(cbIsVoting.isSelected(),convertToParty((String)cbPartys.getValue()),voter.getVotesAtBallotBox(),voter.getId());
					cbPartys.setValue(null);
					cbIsVoting.setSelected(false);
					if (nextVoter()==null) {
						lblName.setVisible(false);
						lblNameValue.setVisible(false);
						lblId.setVisible(false);
						lblIdValue.setVisible(false);
						cbIsVoting.setVisible(false);
						cbPartys.setVisible(false);
						btnVote.setVisible(false);
						lblElectionEnded.setVisible(true);
						electionUIListenable.fireCountVotesEvent();
					}else {
						lblNameValue.setText(voter.getName());
						lblIdValue.setText(voter.getId());
					}
				}

			}
		});

		BackButton btnBack = new BackButton(bgPane,listners);

		GridPane gpBox = new GridPane();
		gpBox.add(lblElectionEnded,0,0,2,1);
		
		gpBox.add(lblName, 0, 0);
		gpBox.add(lblNameValue, 1, 0);

		gpBox.add(lblId, 0, 1);
		gpBox.add(lblIdValue, 1, 1);


		gpBox.add(cbIsVoting, 3, 0);
		gpBox.add(cbPartys, 3, 1);

		gpBox.add(btnStart, 3, 2);
		gpBox.add(btnVote, 3, 2);
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
