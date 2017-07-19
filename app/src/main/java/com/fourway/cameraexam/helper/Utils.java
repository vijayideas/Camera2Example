package com.fourway.cameraexam.helper;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by 4 way on 04-07-2017.
 */

public class Utils {
    public static ArrayList<File> getFilePaths(Activity activity) {

        Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA};
        Cursor c = null;
        SortedSet<String> dirList = new TreeSet<String>();
        ArrayList<File> resultIAV = new ArrayList<File>();

        String[] directories = null;
        if (u != null)
        {
            c = activity.managedQuery(u, projection, null, null, null);
        }

        if ((c != null) && (c.moveToFirst()))
        {
            do
            {
                String tempDir = c.getString(0);
                tempDir = tempDir.substring(0, tempDir.lastIndexOf("/"));
                try{
                    dirList.add(tempDir);
                }
                catch(Exception e)
                {

                }
            }
            while (c.moveToNext());
            directories = new String[dirList.size()];
            dirList.toArray(directories);

        }

        for(int i=0;i<dirList.size();i++)
        {
            File imageDir = new File(directories[i]);
            File[] imageList = imageDir.listFiles();
            if(imageList == null)
                continue;
            for (File imagePath : imageList) {
                try {

                    if(imagePath.isDirectory())
                    {
                        imageList = imagePath.listFiles();

                    }
                    if ( imagePath.getName().contains(".jpg")|| imagePath.getName().contains(".JPG")
                            || imagePath.getName().contains(".jpeg")|| imagePath.getName().contains(".JPEG")
                            || imagePath.getName().contains(".png") || imagePath.getName().contains(".PNG")
//                            || imagePath.getName().contains(".gif") || imagePath.getName().contains(".GIF")
//                            || imagePath.getName().contains(".bmp") || imagePath.getName().contains(".BMP")
                            )
                    {



                        String path= imagePath.getAbsolutePath();
//                        resultIAV.add(Uri.parse(path));
                        resultIAV.add(imagePath);


                    }
                }
                //  }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.sort(resultIAV, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                Long obj1 = o1.lastModified();
                Long obj2 = o2.lastModified();
                return obj1.compareTo(obj2);
            }
        });

        Collections.reverse(resultIAV);

        return resultIAV;


    }
}
