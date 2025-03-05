package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException {
        String path =".//testData//opencart_login_Data.xlsx";

        ExcelUtility xcelUtils= new ExcelUtility(path);

        int totalrows= xcelUtils.getRowCount("Sheet1");
        int totalcols= xcelUtils.getCellCount(1,"sheet1");

        String logindata[][]= new String[totalrows][totalcols];
        for(int i=1;i<=totalrows; i++){

            for(int j=0; j<totalcols; j++){

                logindata[i-1][j]=xcelUtils.getCellData(i,j,"Sheet1");
            }

        }
return logindata;




    }
}
