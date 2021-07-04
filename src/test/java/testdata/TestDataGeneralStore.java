package testdata;

import org.testng.annotations.DataProvider;
import utilities.ExcelUtility;
import utilities.JDBCConnection;

import java.sql.SQLException;

public class TestDataGeneralStore {
    private static final String EXCEL_FILE = System.getProperty("user.dir") + "//src//main//resources//TestData//ExcelTestData.xlsx";

    @DataProvider(name = "inputData")
    public static Object[][] getUserData() {

        //Shital    Argentina   Female
        //Pramod    Nepal       Male

        Object[][] data = new Object[3][3];
        data[0][0] = "Shital"; //[1st row][username]
        data[0][1] = "Nepal"; //[1st row][password]
        data[0][2] = "Female";

        data[1][0] = "Pramod"; //[2nd row][username]
        data[1][1] = "Australia"; //[2nd row][password]
        data[1][2] = "Male";

        data[2][0] = "!@#%&*$"; //[2nd row][username]
        data[2][1] = "Australia"; //[2nd row][password]
        data[2][2] = "Male";
        return data;
    }

    @DataProvider(name = "excelData")
    public static Object[][] getVerifySearchCourseData(){
        String sheetName = "FormFillTests";
        ExcelUtility.setExcelFile(EXCEL_FILE, sheetName);
        return ExcelUtility.getTestData("form_fill_data");
    }

    @DataProvider(name = "fromDatabase")
    public static Object[][] getDataFromDatabase() throws SQLException {
        return JDBCConnection.getDataFromDatabase();
    }

}
