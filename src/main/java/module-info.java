module com.example.ticktacktoegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ticktacktoegame to javafx.fxml;
    exports com.example.ticktacktoegame;
}