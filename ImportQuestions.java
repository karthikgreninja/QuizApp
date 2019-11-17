package com.example.karthikeyan.karthikphplogin;

import android.Manifest;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ImportQuestions extends AppCompatActivity {
    HttpURLConnection httpURLConnection = null;
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    File file;

    Button btnUpDirectory,btnSDCard;
    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;
    String line,result;
    ArrayList<QuestionImport> uploadData;
    ListView lvInternalStorage;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_questions);
        lvInternalStorage = (ListView) findViewById(R.id.lvInternalStorage);
        btnUpDirectory = (Button) findViewById(R.id.btnUpDirectory);
        btnSDCard = (Button) findViewById(R.id.btnViewSDCard);
        uploadData = new ArrayList<>();
        checkFilePermissions();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        lvInternalStorage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastDirectory = pathHistory.get(count);
                if(lastDirectory.equals(adapterView.getItemAtPosition(i))){
                    //Execute method for reading the excel data.
                    readExcelData(lastDirectory);

                }else
                {
                    count++;
                    pathHistory.add(count,(String) adapterView.getItemAtPosition(i));
                    checkInternalStorage();
                }
            }
        });

        btnUpDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == 0){
                    System.out.println("btnUpDirectory: You have reached the highest level directory.");
                }else{
                    pathHistory.remove(count);
                    count--;
                    checkInternalStorage();
                    System.out.println("btnUpDirectory: " + pathHistory.get(count));
                }
            }
        });

        btnSDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                pathHistory = new ArrayList<String>();
                pathHistory.add(count,System.getenv("EXTERNAL_STORAGE"));
                checkInternalStorage();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        }
    }

    private void readExcelData(String filePath) {

        //declare input file
        File inputFile = new File(filePath);

        try {
            InputStream inputStream = new FileInputStream(inputFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            StringBuilder sb = new StringBuilder();

            //outer loop, loops through rows
            for (int r = 1; r < rowsCount; r++) {
                Row row = sheet.getRow(r);
                int cellsCount = row.getPhysicalNumberOfCells();
                //inner loop, loops through columns
                for (int c = 0; c < cellsCount; c++) {
                    //handles if there are to many columns on the excel sheet.
                    if(c>8){
                        toastMessage("ERROR: Excel File Format is incorrect!");
                        break;
                    }
                    else{
                        String value = getCellAsString(row, c, formulaEvaluator);
                        //String cellInfo = "r:" + r + "; c:" + c + "; v:" + value;
                        sb.append(value + ";");
                    }
                }
                sb.append(":");
            }
            parseStringBuilder(sb);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseStringBuilder(StringBuilder mStringBuilder){


        // splits the sb into rows.
        String[] rows = mStringBuilder.toString().split(":");
        System.out.println(rows[0]);
        System.out.println(rows.length);

        for(int i=0; i<rows.length; i++) {
            //Split the columns of the rows
            String[] columns = rows[i].split(";");
            System.out.println(columns[0]);
            System.out.println(columns[1]);
            System.out.println(columns[2]);
            System.out.println(columns[3]);
            System.out.println(columns[4]);
            System.out.println(columns[5]);
            System.out.println(columns[6]);
            System.out.println(columns[7]);

            //use try catch to make sure there are no "" that try to parse into doubles.
            try{
                String QId = (columns[0]);
                String QStatement = (columns[1]);
                String Option1 = (columns[2]);
                String Option2 = (columns[3]);
                String Option3 = (columns[4]);
                String Option4 = (columns[5]);
                String Answer = (columns[6]);
                String TId = (columns[7]);
                //String cellInfo = "(QId,QStatement,Option1,Option2,Option3,Option4,Answer,TId): (" + QId + "," + QStatement + "," + Option1 + "," + Option2 + "," + Option3 + "," + Option4 + "," + Answer + "," + TId +")";
                //add the the uploadData ArrayList
                uploadData.add(new QuestionImport(QId,QStatement,Option1,Option2,Option3,Option4,Answer,TId));

            }catch (NumberFormatException e){

               e.printStackTrace();            }
        }

        printDataToLog();
    }
    String QId;
    String QStatement;
    String Option1;
    String Option2;
    String Option3;
    String Option4;
    String Answer;
    String TId;
    private void printDataToLog() {
        for(int i = 0; i< uploadData.size(); i++){
            QId = uploadData.get(i).getQId();
            QStatement = uploadData.get(i).getQStatement();
            Option1 = uploadData.get(i).getOption1();
            Option2 = uploadData.get(i).getOption2();
            Option3 = uploadData.get(i).getOption3();
            Option4 = uploadData.get(i).getOption4();
            Answer = uploadData.get(i).getAnswer();
            TId = uploadData.get(i).getTId();

            try {
                String URLQuestion = "http://192.168.43.235/QuizApp/QuestionInsert.php?String1="+ QId +"&String2="+ QStatement +"&String3="+ Option1 +"&String4="+ Option2 +"&String5="+ Option3 +"&String6="+ Option4 +"&String7="+ Answer +"&String8=" +TId;
                String URLUpdate = URLQuestion.replace(" ","%20");
                URL url = new URL(URLUpdate);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                is = httpURLConnection.getInputStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + '\n');
                }
                is.close();
                result = stringBuilder.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        toastMessage(result);
    }

    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }

    private void checkInternalStorage() {

        try{
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                toastMessage("No SD card found.");
            }
            else{
                // Locate the image folder in your SD Car;d
                file = new File(pathHistory.get(count));
            }

            listFile = file.listFiles();

            // Create a String array for FilePathStrings
            FilePathStrings = new String[listFile.length];

            // Create a String array for FileNameStrings
            FileNameStrings = new String[listFile.length];

            for (int i = 0; i < listFile.length; i++) {
                // Get the path of the image file
                FilePathStrings[i] = listFile[i].getAbsolutePath();
                // Get the name image file
                FileNameStrings[i] = listFile[i].getName();
            }

            for (int i = 0; i < listFile.length; i++)
            {
                Log.d("Files", "FileName:" + listFile[i].getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FilePathStrings);
            lvInternalStorage.setAdapter(adapter);

        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
