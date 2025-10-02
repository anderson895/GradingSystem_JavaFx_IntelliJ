package com.example.studenttableapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentController {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> courseColumn;
    @FXML
    private TableColumn<Student, Double> averageColumn;
    @FXML
    private TableColumn<Student, Void> actionColumn;
    @FXML
    private Button addButton; // Add new student

    private ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Column bindings
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));

        // Sample data
        students.add(new Student("Alice", "Math", 88.5));
        students.add(new Student("Bob", "Science", 92.0));

        studentTable.setItems(students);

        // Add buttons in action column
        addActionButtons();
    }

    private void addActionButtons() {
        actionColumn.setCellFactory(param -> new TableCell<Student, Void>() {
            private final Button updateBtn = new Button("Update");
            private final Button deleteBtn = new Button("Delete");
            private final HBox pane = new HBox(5, updateBtn, deleteBtn);

            {
                updateBtn.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    showUpdateForm(student);
                });

                deleteBtn.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    students.remove(student);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });
    }

    @FXML
    private void showAddForm() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10;");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField courseField = new TextField();
        courseField.setPromptText("Course");
        TextField averageField = new TextField();
        averageField.setPromptText("Average");

        Button saveBtn = new Button("Add Student");
        saveBtn.setOnAction(e -> {
            String name = nameField.getText();
            String course = courseField.getText();
            double avg;

            try {
                avg = Double.parseDouble(averageField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid average!");
                return;
            }

            if (name.isEmpty() || course.isEmpty()) {
                System.out.println("Please fill all fields!");
                return;
            }

            students.add(new Student(name, course, avg));
            stage.close();
        });

        root.getChildren().addAll(nameField, courseField, averageField, saveBtn);
        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Add Student");
        stage.show();
    }

    private void showUpdateForm(Student student) {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10;");

        TextField nameField = new TextField(student.getName());
        TextField courseField = new TextField(student.getCourse());
        TextField averageField = new TextField(String.valueOf(student.getAverage()));

        Button saveBtn = new Button("Save Changes");
        saveBtn.setOnAction(e -> {
            student.setName(nameField.getText());
            student.setCourse(courseField.getText());

            try {
                student.setAverage(Double.parseDouble(averageField.getText()));
            } catch (NumberFormatException ex) {
                System.out.println("Invalid average!");
                return;
            }

            studentTable.refresh();
            stage.close();
        });

        root.getChildren().addAll(nameField, courseField, averageField, saveBtn);
        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Update Student");
        stage.show();
    }
}
