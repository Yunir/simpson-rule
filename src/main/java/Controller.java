package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.SimpsonRule;

public class Controller {
    @FXML
    private RadioButton i_f1;
    @FXML
    private RadioButton i_f2;
    @FXML
    private RadioButton i_f3;
    @FXML
    private RadioButton i_f4;
    @FXML
    private ToggleGroup i_integrals;
    @FXML
    private TextField i_limitFrom;
    @FXML
    private TextField i_limitTo;
    @FXML
    private ComboBox i_comboBox;
    @FXML
    private Button i_confirmButton;
    @FXML
    private Label i_resultsInfo;
    private SimpsonRule simpsonRule;

    @FXML
    private void initialize() {

    }

    @FXML
    private void computeIntegral(ActionEvent actionEvent) {
        if(checkFields()) {
            int num_of_formula;
            if(i_f1.isSelected()) num_of_formula = 1;
            else if(i_f2.isSelected()) num_of_formula = 2;
            else if(i_f3.isSelected()) num_of_formula = 3;
            else num_of_formula = 4;
            Double from;
            Double to;
            int minus = 1;
            if(Double.parseDouble(i_limitFrom.getText()) < Double.parseDouble(i_limitTo.getText())) {
                from = Double.parseDouble(i_limitFrom.getText());
                to = Double.parseDouble(i_limitTo.getText());
            } else {
                to = Double.parseDouble(i_limitFrom.getText());
                from = Double.parseDouble(i_limitTo.getText());
                minus = -1;
            }
            simpsonRule = new SimpsonRule(num_of_formula, from, to, Double.parseDouble(i_comboBox.getValue().toString()));
            if(simpsonRule.getIsCorrect()) {
                i_resultsInfo.setText("We have answer! :)\n" +
                        "Answer: " + (simpsonRule.getResult()*minus) + "\n" +
                        "Slices: " + simpsonRule.getN() + "\n" +
                        "Offset: " + simpsonRule.getRungeRuleOffset());
            } else {
                i_resultsInfo.setText("We don't have answer!");
            }
        } else {
            i_resultsInfo.setText("You mistake, don't u?");
        }
    }

    private boolean checkFields() {
        if(i_limitFrom.getText().matches("-?((\\d*)|(\\d+\\.\\d*))") &&
                i_limitTo.getText().matches("-?((\\d*)|(\\d+\\.\\d*))") &&
                !i_limitFrom.getText().equals("") && !i_limitTo.getText().equals("") &&
                i_comboBox.getValue() != null) {
            return true;
        } else {
            return false;
        }

    }

}
