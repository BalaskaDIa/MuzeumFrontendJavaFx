package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import hu.petrik.muzeumfrontendjavafx.api.Api;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private TableColumn <Festmeny, Integer> col_FId;
    @FXML
    private TableColumn <Szobor, Integer> col_SzId;
    @FXML
    private TableView <Szobor> table_Szobor;
    @FXML
    private TableView <Festmeny> table_Festmeny;
    @FXML
    private TableColumn <Szobor, Integer> col_SzAr;
    @FXML
    private TableColumn <Szobor, String> col_SzSzemely;
    @FXML
    private TableColumn <Szobor, Integer> col_SzMagas;
    @FXML
    private TableColumn <Festmeny, Integer> col_FEv;
    @FXML
    private TableColumn <Festmeny, Boolean> col_FKiallit;
    @FXML
    private TableColumn <Festmeny, String> col_FCim;

    public void initialize() {
        col_FId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_FCim.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_FEv.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_FKiallit.setCellValueFactory(new PropertyValueFactory<>("on_display"));
    }

    @FXML
    public void btn_SzHozzaad(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_SzTorol(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_FHozzaad(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_FModosit(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_FTorol(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_SzModosit(ActionEvent actionEvent) {
    }
}