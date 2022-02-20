package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import hu.petrik.muzeumfrontendjavafx.api.SzoborApi;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SzoborFelvetelController extends Controller {
    @javafx.fxml.FXML
    private TextField inputSzemely;
    @javafx.fxml.FXML
    private Spinner <Integer> inputMeret;
    @javafx.fxml.FXML
    private Spinner <Integer> inputAr;
    @javafx.fxml.FXML
    private Label lblSzemelyHiba;
    @javafx.fxml.FXML
    private Label lblArHiba;
    @javafx.fxml.FXML
    private Label lblMeretHiba;

    @javafx.fxml.FXML
    public void btn_SzoborFelvesz(Event event) {
        String szemely = inputSzemely.getText();
        if (szemely.isEmpty()) {
            lblSzemelyHiba.setText("A személy megadása kötelező!");
            return;
        } else if (szemely.length() < 5) {
            lblSzemelyHiba.setText("Minimum 5 karakter!");
            return;
        }
        lblSzemelyHiba.setText("");

        int meret = 0;
        try {
            meret = inputMeret.getValue();
        } catch (NullPointerException e) {
            lblMeretHiba.setText("A méret megaádsa kötelező!");
            return;
        } catch (Exception e) {
            lblMeretHiba.setText("A méret 15 és 250 cm között lehet!");
            return;
        }

        if (meret < 15 || meret > 250) {
            lblMeretHiba.setText("A méret 15 és 250 cm között lehet!");
            return;
        }
        lblMeretHiba.setText("");

        int ar = 0;
        try {
            ar = inputAr.getValue();
        } catch (NullPointerException e) {
            lblArHiba.setText("Az ár megadása kötelező!");
            return;
        } catch (Exception e) {
            lblArHiba.setText("Az ár 1000 és 500000 között lehet!");
            return;
        }

        if (ar < 1000 || ar > 500000) {
            lblArHiba.setText("Az ár 1000 és 500000 között lehet!");

            return;
        }
        lblArHiba.setText("");

        try {
            Szobor uj = new Szobor(0, szemely, meret, ar);
            Szobor letrehozott = SzoborApi.post(uj);
            if (letrehozott != null) {
                alert("Sikeres hozzáadás!");
                inputSzemely.setText("");
                inputMeret.getValueFactory().setValue(50);
                inputAr.getValueFactory().setValue(10000);
            } else {
                alert("Sikertelen hozzáadás!");
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}
