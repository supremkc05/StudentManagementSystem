module com.studentmanagementsystem.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.xml;
    //requires de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
    //requires org.apache.commons.csv;

    opens com.studentmanagementsystem.studentmanagementsystem to javafx.fxml;
    exports com.studentmanagementsystem.studentmanagementsystem;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;
    exports com.studentmanagementsystem.studentmanagementsystem.Models;
    exports com.studentmanagementsystem.studentmanagementsystem.Views;
}