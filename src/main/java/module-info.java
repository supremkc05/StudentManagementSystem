module com.studentmanagementsystem.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.xml;
    requires java.desktop;
    //requires de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
    //requires org.apache.commons.csv;
    opens com.studentmanagementsystem.studentmanagementsystem.Controllers.Students to javafx.fxml;
    opens com.studentmanagementsystem.studentmanagementsystem.Controllers.Teacher to javafx.fxml;
    opens com.studentmanagementsystem.studentmanagementsystem to javafx.fxml;
    opens com.studentmanagementsystem.studentmanagementsystem.Controllers.libarian to javafx.fxml;
    opens com.studentmanagementsystem.studentmanagementsystem.Controllers.AdmissionOffiicer to javafx.fxml;
    exports com.studentmanagementsystem.studentmanagementsystem;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers.Teacher to javafx.fxml;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;
    exports com.studentmanagementsystem.studentmanagementsystem.Models;
    exports com.studentmanagementsystem.studentmanagementsystem.Views;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers.AdmissionOffiicer;
    exports com.studentmanagementsystem.studentmanagementsystem.Controllers.libarian;
    opens com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin to javafx.fxml;
    

}