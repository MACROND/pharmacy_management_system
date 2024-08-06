package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.DrugController;
import com.example.app.controllers.SaleController;
import com.example.app.controllers.StockController;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Stock;
import com.example.app.utils.algorithms.Functions;
import eu.hansolo.tilesfx.addons.Switch;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ManageSaleController {

    private final SaleController saleController = new SaleController();
    public MainDashboardController mainController;

    public void setMainController(MainDashboardController<Sale> mainController) {
        this.mainController = mainController;
    }

    @FXML
    private TextField drugIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerContactField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField totalPriceField;
    @FXML
    private TextField searchField;
    @FXML
    private TextField deleteField;

    @FXML
    public CheckBox sortByTimeAsc;
    public CheckBox sortByTimeDesc;
    public CheckBox sortByAmtAsc;
    public CheckBox sortByAmtDesc;

    @FXML
    private void handleAddSale() {
        String drugId = drugIdField.getText();
        String customerName = customerNameField.getText();
        String customerContact = customerContactField.getText();
        LocalDateTime date = LocalDate.now().atStartOfDay();
        int quantity = parseInt(quantityField.getText());

        // Record Sales data
        List<Object> result = DrugController.getDrugByName(drugId);
        List<Drug> drugList = (List<Drug>) result.getFirst();
        Drug drug = drugList.getFirst();

        double unitPrice = drug.getPrice();
        double totalPrice = unitPrice * quantity;

        Sale sale = new Sale(drugId, date, quantity, totalPrice, customerName, customerContact);
        saleController.addSale(sale);

        // Update stock data of the sold drug
        Stock stock = StockController.getStockById(drugId).getFirst();
        int amountSold = stock.getAmountSold() + quantity;
        int quantityLeft = stock.getInitialQuantity() - amountSold;

        Stock stockUpdate = new Stock(drugId, stock.getName(), stock.getInitialQuantity(), quantityLeft, amountSold,
                date,
                updateStockStatus(drugId, quantityLeft));

        StockController.updateStock(stockUpdate);

        // Clear the fields after adding
        drugIdField.clear();
        customerNameField.clear();
        customerContactField.clear();
        quantityField.clear();

        mainController.clearReportField();
    }

    @FXML
    private void handleViewAllSales() {
        sortByTimeAsc.setSelected(false);
        sortByTimeDesc.setSelected(false);
        sortByAmtAsc.setSelected(false);
        sortByAmtDesc.setSelected(false);
        mainController.configureTableForSalesHistory(SaleController.getAllSales());
        mainController.clearReportField();
    }

    @FXML
    private void handleDeleteSale() {
        String id = deleteField.getText();
        mainController.configureTableForSalesHistory(SaleController.deleteSale(parseInt(id)));

        deleteField.clear();
        mainController.clearReportField();
    }

    @FXML
    private void handleSearchSale() {
        String id = searchField.getText();
        List<Object> result = SaleController.searchSale(id);
        List<Sale> searchedSale = (List<Sale>) result.getFirst();
        String report = (String) result.get(1);
        mainController.configureTableForSalesHistory(searchedSale);
        mainController.configureFieldForGeneratedReport(report);

        searchField.clear();

    }

    /**
     * This Java function sorts sales by time in ascending order and updates the UI
     * accordingly.
     */
    @FXML
    private void sortSalesByTimeAsc() {
        List<Sale> saleList = (List<Sale>) Functions.sortSalesByTimeAsc().getFirst();
        String report = (String) Functions.sortSalesByTimeAsc().get(1);

        if (sortByTimeAsc.isSelected()) {
            sortByTimeDesc.setSelected(false);
            sortByAmtAsc.setSelected(false);
            sortByAmtDesc.setSelected(false);
            mainController.configureTableForSalesHistory(saleList);
            mainController.configureFieldForGeneratedReport(report);
        } else {
            sortByTimeAsc.setSelected(false);
            sortByTimeDesc.setSelected(false);
            sortByAmtAsc.setSelected(false);
            sortByAmtDesc.setSelected(false);
            handleViewAllSales();
            mainController.clearReportField();
        }
    }

    /**
     * The `sortSalesByTimeDesc` function sorts sales by time in descending order
     * and updates the UI
     * accordingly.
     */
    @FXML
    private void sortSalesByTimeDesc() {
        List<Sale> saleList = (List<Sale>) Functions.sortSalesByTimeDesc().getFirst();
        String report = (String) Functions.sortSalesByTimeDesc().get(1);

        if (sortByTimeDesc.isSelected()) {
            sortByTimeAsc.setSelected(false);
            sortByAmtAsc.setSelected(false);
            sortByAmtDesc.setSelected(false);
            mainController.configureTableForSalesHistory(saleList);
            mainController.configureFieldForGeneratedReport(report);
        } else {
            sortByTimeAsc.setSelected(false);
            sortByTimeDesc.setSelected(false);
            sortByAmtAsc.setSelected(false);
            sortByAmtDesc.setSelected(false);
            handleViewAllSales();
            mainController.clearReportField();
        }
    }

    /**
     * This Java function handles sorting sales by purchase amount in ascending
     * order and updates the UI
     * accordingly.
     */
    @FXML
    public void handleSortPurchaseAmountAsc() {
        List<Sale> saleList = (List<Sale>) Functions.sortSaleByPurchaseAmountAsc().getFirst();
        String report = (String) Functions.sortSaleByPurchaseAmountAsc().get(1);

        if (sortByAmtAsc.isSelected()) {
            sortByTimeAsc.setSelected(false);
            sortByTimeDesc.setSelected(false);
            sortByAmtDesc.setSelected(false);
            mainController.configureTableForSalesHistory(saleList);
            mainController.configureFieldForGeneratedReport(report);
        } else {
            sortByTimeAsc.setSelected(false);
            sortByTimeDesc.setSelected(false);
            sortByAmtAsc.setSelected(false);
            sortByAmtDesc.setSelected(false);
            handleViewAllSales();
            mainController.clearReportField();
        }
    }

    /**
     * This Java function handles sorting a list of sales by purchase amount in
     * descending order and
     * updates the UI accordingly.
     */
    @FXML
    public void handleSortPurchaseAmountDesc() {
        List<Sale> saleList = (List<Sale>) Functions.sortSaleByPurchaseAmountDesc().getFirst();
        String report = (String) Functions.sortSaleByPurchaseAmountDesc().get(1);

        if (sortByAmtDesc.isSelected()) {
            sortByTimeAsc.setSelected(false);
            sortByTimeDesc.setSelected(false);
            sortByAmtAsc.setSelected(false);
            mainController.configureTableForSalesHistory(saleList);
            mainController.configureFieldForGeneratedReport(report);
        } else {
            sortByTimeAsc.setSelected(false);
            sortByTimeDesc.setSelected(false);
            sortByAmtAsc.setSelected(false);
            sortByAmtDesc.setSelected(false);
            handleViewAllSales();
            mainController.clearReportField();
        }
    }

    /**
     * This Java function determines the stock status based on the quantity left
     * compared to the
     * initial quantity.
     * 
     * @param drugId       The `drugId` parameter is a unique identifier for a
     *                     specific drug in the system.
     *                     It is used to identify and track the stock status of that
     *                     particular drug.
     * @param quantityLeft The `quantityLeft` parameter represents the remaining
     *                     quantity of a drug in
     *                     stock. The `updateStockStatus` method uses this parameter
     *                     along with the initial quantity of the
     *                     drug to determine the current stock status of the drug.
     *                     The method checks the percentage of
     *                     quantity left compared to the initial quantity to
     *                     categorize
     * @return The method `updateStockStatus` returns a String value indicating the
     *         stock status based
     *         on the quantity left compared to the initial quantity of the stock.
     *         The possible return values
     *         are "Low", "Moderate", or "High" depending on the conditions met in
     *         the if-else statements.
     */
    private String updateStockStatus(String drugId, int quantityLeft) {
        Stock stockData = StockController.getAllStock().getFirst();
        assert stockData != null;
        if (quantityLeft <= 0.1 * stockData.getInitialQuantity()) {
            return "Low";
        } else if (quantityLeft <= 0.8 * stockData.getInitialQuantity()) {
            return "Moderate";
        } else {
            return "High";
        }
    }
}
