import java.io.*;

public class DataManage {
    private String path = "C:\\Program Files\\Tetris";

    public boolean saveData (int arr_[][], int score_){
        int[][] data = arr_;
        int x = data[0].length - 1;
        int y = data.length - 1;
        data[y][x] = score_;

        ObjectOutputStream objOut = null;
        FileOutputStream fileOut = null;
        try{
            File folder = new File(path);
            //계속 에러 뜨는데 관리자 권한으로 실행한다.
            if(!folder.exists()) {
                if(folder.mkdir())
                    System.out.println("Success");
                else{
                    System.out.println("Failed");
                }
            }
            path = path + "/Tetris.dat";
            File myFile = new File(path);
            if(!myFile.exists())
                myFile.createNewFile();

            fileOut = new FileOutputStream(myFile, false);
            objOut= new ObjectOutputStream(fileOut);
            objOut.writeObject(data);
            if(objOut != null)
                objOut.close();
            else if (fileOut != null)
                fileOut.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public int[][] loadData(){
        int[][] data = null;
        String pathTo = path + "/Tetris.dat";
        ObjectInputStream objInputStream = null;

        FileInputStream inputStream = null;

        try{

            inputStream = new FileInputStream(pathTo);

            objInputStream = new ObjectInputStream (inputStream);

            data = (int[][]) objInputStream.readObject();

            objInputStream.close();



        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }finally{

            if (objInputStream != null){

                try{objInputStream.close();}catch (Exception e){}

            }

            else if (inputStream != null){

                try{inputStream.close();}catch (Exception e){}

            }

        }
        return data;
    }
}
