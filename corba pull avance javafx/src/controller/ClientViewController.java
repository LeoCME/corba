package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import dialogue.Dialogue;
import dialogue.notExistingPseudo;
import dialogueApp.Client;
import dialogueApp.MsgGetter;

public class ClientViewController {
	@FXML
	private TextField labelPseudo;
	@FXML
	private TextArea listeMessages;
	@FXML
	private Button boutonPseudoValider;
	@FXML
	private Button bouttonMiseAJourDestinataires;
	@FXML
	private ComboBox<String> choiceBoxDestinataires;
	@FXML
	private TextField textFieldInputMessage;
	@FXML
	private Button bouttonSend;

	private Client mainApp;
	private Dialogue dialogue;

	public ClientViewController() {
	}

	@FXML
	private void initialize() {

	}

	public void setMainApp(Client client) {
		this.mainApp = client;
	}

	// Ecoute le bouton valider pseudo
	@FXML
	private void handleBoutonValider() {
		String pseudo = labelPseudo.getText();
		if (!"".equals(pseudo)) {
			dialogue = Client.maConnexion.connect(labelPseudo.getText());

			MsgGetter mg = new MsgGetter(dialogue, this);
			mg.start();
			//Platform.runLater(mg);
			// listeMessages.setText(pseudo);
			bouttonMiseAJourDestinataires.setDisable(false);
			choiceBoxDestinataires.setDisable(false);
			handleBoutonMiseAJour();
			boutonPseudoValider.setDisable(true);
			labelPseudo.setDisable(true);
		}

	}

	// Ecoute le boutton de mise à jour des destinataires
	@FXML
	private void handleBoutonMiseAJour() {
		String[] listeClients = dialogue.getClients();
		ObservableList<String> listeClientsObs = FXCollections
				.observableArrayList();
		// listeClientsObs.addAll(listeClients);

		for (int i = 0; i < listeClients.length; i++) {
			listeClientsObs.add(listeClients[i]);
		}
		choiceBoxDestinataires.setItems(listeClientsObs);
	}

	@FXML
	private void handleComboBoxAction() {
		bouttonSend.setDisable(false);
		textFieldInputMessage.setDisable(false);
	}

	@FXML
	private void handleSendMessage() throws notExistingPseudo {
		String message = textFieldInputMessage.getText();
		if (!"".equals(message)) {
			dialogue.sendMessage(choiceBoxDestinataires.getValue(), message);
			listeMessages.appendText("\n"+"moi: " + message);
			textFieldInputMessage.clear();
		}
	}

	public void receiveMessage(String s) {
		listeMessages.appendText("\n" + s);
	}

}
