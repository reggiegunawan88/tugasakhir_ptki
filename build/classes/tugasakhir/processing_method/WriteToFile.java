/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir.processing_method;

import java.io.*;

/**
 *
 * @author reggi
 */
public class WriteToFile {
    /**
     * Use FileWriter when number of write operations are less
     *
     * @param data
     */
    public void writeUsingFileWriter(String namaDoc, String data) {
        String dir = System.getProperty("user.dir") + "\\cleaned_dataset\\";
        File file = new File(dir);
        file.mkdirs();
        File doc = new File(dir + namaDoc);
        FileWriter fr = null;
        try {
            fr = new FileWriter(doc);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
